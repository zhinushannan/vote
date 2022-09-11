<template>
  <div class="register-wrap">
    <div class="ms-register">
      <div class="ms-title">实干青年投票系统</div>
      <el-form :model="param" :rules="rules" ref="register" label-width="0px" class="ms-content">
        <el-form-item prop="phone">
          <el-input v-model="param.phone" placeholder="请输入手机号">
            <el-button slot="prepend" icon="el-icon-lx-mobile"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              type="password"
              placeholder="请输入密码"
              v-model="param.password"
              @keyup.enter.native="submitForm()"
          >
            <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item prop="repeatPassword">
          <el-input
              type="password"
              placeholder="请重复密码"
              v-model="param.repeatPassword"
              @keyup.enter.native="submitForm()"
          >
            <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
          </el-input>
        </el-form-item>
        <div class="register-btn">
          <el-button type="primary" @click="submitForm()">注册</el-button>
        </div>
      </el-form>
    </div>

    <!-- 完善信息 begin -->
    <el-dialog
        title="请按提示完善信息"
        :visible.sync="informationVisible"
        width="30%"
        :before-close="informationHandleClose">
      <el-form :model="informationForm" :rules="informationRules" ref="information" label-width="100px"
               class="demo-ruleForm">
        <el-form-item label="姓名" prop="name">
          <el-input type="name" v-model="informationForm.name" autocomplete="off" placeholder="请输入您的姓名"
                    style="width: 182px" maxlength="10" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="informationForm.sex" placeholder="请选择性别">
            <el-option
                v-for="item in sex"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="省份" prop="province">
          <el-select v-model="informationForm.provinceId" placeholder="请选择省份">
            <el-option
                v-for="item in province"
                :key="item.provinceId"
                :label="item.provinceName"
                :value="item.provinceId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="informationForm.roleId" placeholder="请选择角色">
            <el-option
                v-for="item in role"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitInformationForm">提 交</el-button>
      </span>
    </el-dialog>
    <!-- 完善信息 end -->


    <!-- 注册新角色 -->
    <el-dialog
        title="提示"
        :visible.sync="roleVisible"
        width="30%">
      <span>{{ roleMessage }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="roleVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRole">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 注册新角色 -->

  </div>
</template>

<script>
export default {
  data: function () {
    return {
      param: {
        phone: '18762237071',
        password: '18762237071',
        repeatPassword: '18762237071',
      },
      rules: {
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 11, message: '长度在 6 到 11 个字符', trigger: 'blur'}
        ],
        repeatPassword: [
          {required: true, message: '请输入重复密码', trigger: 'blur'},
          {min: 6, max: 11, message: '长度在 6 到 11 个字符', trigger: 'blur'}
        ],
      },
      informationVisible: false,
      informationForm: {},
      informationRules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在10个字符以内', trigger: 'blur'}
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'blur'},
        ],
        provinceId: [
          {required: true, message: '请选择省份', trigger: 'blur'},
        ],
        roleId: [
          {required: true, message: '请选择角色', trigger: 'blur'},
        ],
      },
      sex: [
        {value: 'male', label: '男'},
        {value: 'female', label: '女'}
      ],
      role: [
        {value: '1', label: '普通用户'},
        {value: '2', label: '管理员'}
      ],
      province: [],

      roleVisible: false,
      roleMessage: '',
    };
  },
  methods: {
    submitForm() {
      let _this = this
      this.$refs.register.validate(valid => {
        if (valid) {
          if (_this.param['password'] !== _this.param['repeatPassword']) {
            _this.$message.error('两次密码不相同！');
          }
          _this.$axios.post("common/register/do", _this.param).then((resp) => {
            let data = resp.data;
            // 权限
            if (data.code === 100) {
              _this.roleMessage = data.message
              _this.roleVisible = true
            }
            // 完善信息
            else if (data.code === 200) {
              _this.$message.success(data.message);
              _this.informationVisible = true
              _this.province = data.data
            } else if (data.code === 403) {
              _this.$message.error(data.message);
            } else {
              _this.$message.error(data.message);
            }
          })
        } else {
          _this.$message.error('请按照提示输入正确的参数！');
          return false;
        }
      });
    },
    informationHandleClose(done) {
      this.$confirm('确认不再完善信息？这将导致您暂时无法登陆')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
    },
    submitInformationForm() {
      let _this = this
      this.$refs.information.validate(valid => {
        if (valid) {
          let dto = {
            phone: _this.param['phone'],
            password: _this.param['password'],
            name: _this.informationForm['name'],
            provinceId: _this.informationForm['provinceId'],
            sex: _this.informationForm['sex'],
            roleId: _this.informationForm['roleId']
          }
          _this.$axios.post("common/register/info", dto).then((resp) => {
            let data = resp.data
            if (data.flag) {
              _this.$message.success("注册成功，即将跳往登陆界面")
              setTimeout(function () {
                _this.$router.push('/login');
              }, 1500)
            }
          })
        } else {
          _this.$message.error("请按照提示完善信息！！")
        }
      })
    },
    addRole() {
      let _this = this
      this.$refs.register.validate(valid => {
        if (valid) {
          _this.$axios.post("common/register/addRole", _this.param).then((resp) => {
            let data = resp.data
            if (data.flag) {
              _this.$message.success(data.message)
              setTimeout(function () {
                _this.$router.push('/login');
              }, 1500)
            } else {
              _this.$message.error(data.message)
            }
          })
        } else {
          _this.$message.error("请按要求填写字段！")
          _this.informationVisible = false
        }
      })
    }
  },
};
</script>

<style scoped>
.register-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(../../assets/img/login-bg.jpg);
  background-size: 100%;
}

.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: rgba(0, 0, 0, 0.8);
  border-bottom: 1px solid #ddd;
}

.ms-register {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -250px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.6);
  overflow: hidden;
}

.ms-content {
  padding: 30px 30px;
}

.register-btn {
  text-align: center;
}

.register-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}

.register-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}
</style>
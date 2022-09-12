<template>
  <section class="main">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-rank"></i> 投票管理</el-breadcrumb-item>
        <el-breadcrumb-item>新建投票</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div style="width: 50%">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="投票名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="投票简介">
            <el-input type="textarea" v-model="form.description"></el-input>
          </el-form-item>
          <el-form-item label="截止时间">
            <el-date-picker
                v-model="form.deadline"
                type="datetime"
                :picker-options="pickerOptions"
                placeholder="选择日期时间" />
            <p style="color: red; font-size: 5px">不选择表示永久有效，除非您手动截止</p>
          </el-form-item>
          <el-form-item label="候选人">
            <el-transfer
                filterable
                filter-placeholder="模糊搜索"
                :titles="['候选人', '已选候选人']"
                v-model="form.candidateIds"
                :props="{
                  key: 'candidateId',
                  label: 'name'
                }"
                target-order="push"
                :data="candidates">
            </el-transfer>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submit">表单提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "save",
  data() {
    return {
      form: {},
      candidates: [],
      pickerOptions:{
        disabledDate (time) {
          return time.getTime() < Date.now()
        }
      },
    }
  },
  methods: {
    submit() {
      let _this = this
      if (!_this.form['name']) {
        _this.$message.error("请输入投票名称")
        return
      }
      if (!_this.form['description']) {
        _this.$message.error("请输入投票简介")
        return
      }
      if (!_this.form['candidateIds'] || _this.form['candidateIds'].length === 0) {
        _this.$message.error("请选择候选人")
        return
      }
      _this.$axios.post("admin/vote/save", _this.form).then((resp) => {
        console.log(resp)
      })
    }
  },
  mounted() {
    let _this = this
    _this.$axios.get("admin/candidate/list?page=-1&size=-1").then((resp) => {
      _this.candidates = resp.data.data.data
    })
  }
}
</script>

<style scoped>

</style>
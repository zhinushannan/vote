<template>

  <section class="main">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-rank"></i> 候选人管理</el-breadcrumb-item>
        <el-breadcrumb-item>查看候选人</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-row :gutter="10" width="100%">
        <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="16">

          <el-row :gutter="20">
            <el-col :span="6" v-for="(item, index) in page.data" :key="index" style="margin-bottom: 25px">
              <el-card :body-style="{ padding: '0px' }">
                <el-image
                    style="width: 100%; height: 220px"
                    :src="item.imgUrl"
                    :preview-src-list="[item.imgUrl]"
                    fit="cover">
                </el-image>
                <div style="padding: 14px;">
                  <span>{{ item.name }}</span>
                  <span>{{ item.abstractOfCandidate }}</span>
                  <div class="bottom clearfix">
                    <el-popover
                        placement="right"
                        width="600"
                        trigger="click">
                      <el-table :data="itemHistory[item['candidateId']]">
                        <el-table-column property="versionId" label="版本号"></el-table-column>
                        <el-table-column property="modifyTimestamp" label="创建时间"></el-table-column>
                        <el-table-column property="versionDescription" label="版本描述"></el-table-column>
                        <el-table-column
                            label="操作">
                          <template scope="scope">
                            <el-button
                                @click.native.prevent="version(scope.$index, item['candidateId'])"
                                type="text"
                                size="small">
                              查看
                            </el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                      <el-button slot="reference" type="text" class="button" @click="getHistory(item)">历史版本
                      </el-button>
                    </el-popover>

                    <el-button type="text" class="button" @click="introduction(item)">查看简介</el-button>
                    <el-button type="text" class="button" @click="edit(item)">编辑</el-button>
                    <el-popconfirm
                        title="删除后不可恢复，确定要删除吗？"
                        @confirm="del(item)"
                    >
                      <el-button slot="reference" type="text" class="button">删除</el-button>
                    </el-popconfirm>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>

        </el-col>
        <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <h1 ref="introduction_h1"></h1>
            </div>
            <div ref="introduction_div"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>


    <el-dialog
        :title="form.title"
        :visible.sync="editVisible"
        width="80%">

      <el-form ref="form" :model="form">
        <el-form-item label="姓名" style="width: 60%">
          <el-input v-model="form.name" maxlength="10" :show-word-limit=true></el-input>
        </el-form-item>
        <el-form-item label="摘要" style="width: 60%">
          <el-input type="textarea" v-model="form.abstractOfCandidate" maxlength="500" :show-word-limit=true></el-input>
        </el-form-item>
        <el-form-item label="上传图片" style="width: 30%">
          <br>
          <el-upload
              action="http://localhost:8001/common/image/upload"
              list-type="picture-card"
              :limit="1"
              :file-list="form.fileList"
              :on-success="uploadSuccess"
              :on-exceed="exceed"
              :on-preview="handlePictureCardPreview">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="简介">
          <br>
          <mavon-editor v-model="form.content" ref="md" @imgAdd="$imgAdd" @change="change" style="min-height: 100px"/>
        </el-form-item>
        <el-form-item label="版本描述" style="width: 60%">
          <el-input v-model="form.versionDescription" maxlength="30" :show-word-limit=true></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">更新版本</el-button>
      </span>
    </el-dialog>

  </section>

</template>

<script>
import {mavonEditor} from "mavon-editor";
import 'mavon-editor/dist/css/index.css'

export default {
  name: "list",
  components: {
    mavonEditor,
  },
  data() {
    return {
      page: {
        page: 1,
        size: 8,
        total: 0,
        data: []
      },
      itemHistory: {},
      editVisible: false,
      form: {},
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  methods: {
    list() {
      let _this = this
      _this.$axios.get(`admin/candidate/list?page=${_this.page['page']}&size=${_this.page['size']}`).then((resp) => {
        _this.page = resp.data.data
      })
    },
    introduction(item) {
      let _this = this
      _this.$refs['introduction_h1'].innerHTML = "候选人：" + item.name + "简介"
      _this.$refs['introduction_div'].innerHTML = item.introductionHtml
    },
    getHistory(item) {
      let _this = this
      if (_this.itemHistory[item['candidateId']]) {
        return
      }
      _this.$axios.get(`admin/candidate/history?candidateId=${item['candidateId']}`).then((resp) => {
        _this.itemHistory[item['candidateId']] = resp.data.data
      })
    },
    version(index, candidateId) {
      let _this = this
      let item = _this.itemHistory[candidateId][index]
      _this.$refs['introduction_h1'].innerHTML = "候选人：" + item.candidateName + "简介（版本：" + item['versionId'] + "）"
      _this.$refs['introduction_div'].innerHTML = item.introductionHtml
    },
    edit(item) {
      let _this = this
      _this.form['title'] = `修改${item.name}`
      _this.$set(_this.form, 'name', item['name'])
      _this.$set(_this.form, 'abstractOfCandidate', item['abstractOfCandidate'])
      _this.form['imgUrl'] = item.imgUrl
      _this.form['fileList'] = [{name: '1', url: item.imgUrl}]
      _this.form['content'] = item.introductionMd
      _this.form['candidateId'] = item.candidateId
      _this.editVisible = true
    },
    del(item) {
      let _this = this
      _this.$axios.get(`admin/candidate/delete?candidateId=${item.candidateId}`).then((resp) => {
        if (resp.data.flag) {
          _this.$message.success(resp.data.message)
          _this.list()
        }
      })
    },
    // 将图片上传到服务器，返回地址替换到md中
    $imgAdd(pos, $file) {
      let formdata = new FormData();
      formdata.append('file', $file);
      // 这里没有服务器供大家尝试，可将下面上传接口替换为你自己的服务器接口
      this.$axios({
        url: '/common/image/upload',
        method: 'post',
        data: formdata,
        headers: {'Content-Type': 'multipart/form-data'},
      }).then((url) => {
        this.$refs.md.$img2Url(pos, url.data.data.url);
      })
    },
    change(value, render) {
      // render 为 markdown 解析后的结果
      this.html = render;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    exceed(file, fileList) {
      this.$message.info('只允许上传一张照片')
    },
    uploadSuccess(response, file, fileList) {
      this.form['imgUrl'] = response.data.url
    },
    submit() {
      this.form['introductionHtml'] = this.html
      this.form['introductionMd'] = this.form['content']
      this.form['versionId'] = 1
      if (!this.form['name']) {
        this.$message.error("请输入姓名")
        return
      }
      if (!this.form['abstractOfCandidate']) {
        this.$message.error("请输入摘要")
        return
      }
      if (!this.form['imgUrl']) {
        this.$message.error("请上传图片（封面）")
        return
      }
      if (!this.form['introductionMd']) {
        this.$message.error("请输入简介")
        return
      }
      let _this = this
      _this.$axios.post("admin/candidate/update", _this.form).then((resp) => {
        if (resp.data.flag) {
          _this.$message.success(resp.data.message)
          _this.form = {}
          _this.editVisible = false
        } else {
          _this.$message.error(resp.data.message)
        }
      })
    }
  },
  mounted() {
    let _this = this
    _this.list()
  }
}
</script>

<style scoped>

</style>
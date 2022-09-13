<template>

  <section class="main">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-rank"></i> 候选人管理</el-breadcrumb-item>
        <el-breadcrumb-item>新建候选人</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div style="width: 100%">
        <el-form ref="form" :model="form">
          <el-form-item label="姓名" style="width: 30%">
            <el-input v-model="form.name" maxlength="10" :show-word-limit=true></el-input>
          </el-form-item>
          <el-form-item label="摘要" style="width: 30%">
            <el-input type="textarea" v-model="form.abstractOfCandidate" maxlength="500"
                      :show-word-limit=true></el-input>
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
            <mavon-editor v-model="form.content" ref="md" @imgAdd="$imgAdd" @change="change" style="min-height: 300px"/>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submit">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </section>

</template>

<script>
import {mavonEditor} from "mavon-editor";
import 'mavon-editor/dist/css/index.css'
import UploadCropper from 'vue-upload-cropper'

export default {
  name: "save",
  data() {
    return {
      form: {},
      dialogImageUrl: '',
      dialogVisible: false
    }
  },
  components: {
    mavonEditor,
    UploadCropper
  },
  methods: {
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
      _this.$axios.post("admin/candidate/save", _this.form).then((resp) => {
        if (resp.data.flag) {
          _this.$message.success(resp.data.message)
          _this.form = {}
        }
        console.log(resp)
      })
    }
  }
}
</script>

<style scoped>

</style>
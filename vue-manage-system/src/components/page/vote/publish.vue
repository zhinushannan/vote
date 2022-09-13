<template>
  <section class="main">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-rank"></i> 投票管理</el-breadcrumb-item>
        <el-breadcrumb-item>查看投票</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <el-table
          :data="page.data"
          border
          class="table"
          ref="multipleTable"
      >
        <el-table-column prop="title" label="投票名称" align="center"/>
        <el-table-column prop="description" label="投票描述" align="center"/>
        <el-table-column
            prop="createTimestamp"
            width="180"
            label="创建时间"
            align="center"
            :formatter="dateFormat1"
        />
        <el-table-column
            prop="deadline"
              width="180"
            label="截止时间"
            align="center"
            :formatter="dateFormat2"
        />
        <el-table-column label="操作" width="400" align="center">
          <template slot-scope="scope">
            <el-button
                type="text"
                icon="el-icon-document-copy"
                v-clipboard:copy="'http://localhost:8080/#/vote/page/' + scope.row.voteId"
                v-clipboard:success="function (){
                  $message.success('复制成功！')
                }"
                v-clipboard:error="function () {
                  $message.error('复制失败，请手动复制：http://localhost:8080/#/vote/page/' + scope.row.voteId)
                }"
            >复制链接
            </el-button>
            <el-button
                type="text"
                icon="el-icon-edit"
                @click="getSort(scope.$index, scope.row)"
            >查看排名
            </el-button>
            <el-button
                type="text"
                icon="el-icon-edit"
                @click="info(scope.$index, scope.row)"
            >查看详情
            </el-button>
            <el-button
                type="text"
                icon="el-icon-stopwatch"
                class="red"
                @click="stopVote(scope.$index, scope.row)"
            >截止（提前截止）
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>


    <el-dialog
        :title="sortInfo.title"
        :visible.sync="sortVisible"
        width="25%">
      <el-table
          :data="sortInfo.data"
          style="width: 100%"
      >
        <el-table-column
            prop="candidateName"
            label="姓名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="score"
            label="得票数"
            sortable
            width="180">
        </el-table-column>
      </el-table>
    </el-dialog>

  </section>
</template>

<script>
export default {
  name: "publish",
  data() {
    return {
      page: {
        page: 1,
        size: 10,
        total: 0,
        data: []
      },
      sortVisible: false,
      sortInfo: {}
    }
  },
  methods: {
    list() {
      let _this = this
      _this.$axios.get(`admin/vote/list?page=${_this.page.page}&size=${_this.page.size}&status=0`).then((resp) => {
        _this.page = resp.data.data
      })
    },
    dateFormat1(row) {
      if (!row.createTimestamp) {
        return "未指定"
      }
      return row.createTimestamp.slice(0, 19).replace("T", " ")
    },
    dateFormat2(row) {
      if (!row.deadline) {
        return "未指定"
      }
      return row.deadline.slice(0, 19).replace("T", " ")
    },
    stopVote(index, row) {
      let _this = this
      _this.$axios.get(`admin/vote/stop?voteId=${row.voteId}`).then((resp) => {
        if (resp.data.flag) {
          _this.$message.success("停止成功")
          _this.list()
        }
      })
    },
    getSort(index, row) {
      let _this = this
      _this.sortInfo = {}
      _this.$axios.get(`admin/vote/sort?voteId=${row.voteId}`).then((resp) => {
        _this.sortInfo["data"] = resp.data.data
        _this.sortInfo["title"] = `${row.title}的排名`
        _this.sortVisible = true
      })
    },
    info(index, row) {
      let _this = this
      _this.$axios.get(`admin/vote/info?voteId=${row.voteId}`).then((resp) => {

      })
    }
  },
  mounted() {
    let _this = this

    _this.list();
  }
}
</script>

<style scoped>

</style>
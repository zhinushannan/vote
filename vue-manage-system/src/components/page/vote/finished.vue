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
                icon="el-icon-edit"
                @click="del(scope.$index, scope.row)"
            >删除
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

    <el-dialog
        :title="voteInfo.title"
        :visible.sync="voteVisible"
        width="47%">
      <el-table
          :data="voteInfo.data"
          style="width: 100%"
      >
        <el-table-column
            prop="userId"
            label="用户ID"
            align="center"
            width="60"/>
        <el-table-column
            prop="username"
            label="用户姓名"
            align="center"
            width="180"/>
        <el-table-column
            prop="score"
            label="投票分数"
            align="center"
            width="50"/>
        <el-table-column
            prop="candidateName"
            label="候选人"
            align="center"
            width="150"/>
        <el-table-column
            prop="createTimestamp"
            label="投票时间"
            align="center"
            :formatter="dateFormat1"
            width="180"/>
        <el-table-column
            prop="ip"
            label="投票IP"
            align="center"
            width="100"/>
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
      sortInfo: {},
      voteVisible: false,
      voteInfo: {}
    }
  },
  methods: {
    list() {
      let _this = this
      _this.$axios.get(`admin/vote/list?page=${_this.page.page}&size=${_this.page.size}&status=1`).then((resp) => {
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
      _this.voteInfo = {}
      _this.$axios.get(`admin/poll/info?voteId=${row.voteId}`).then((resp) => {
        _this.voteInfo["data"] = resp.data.data
        _this.voteInfo["title"] = `${row.title}的详情`
        console.log(_this.voteInfo)
        _this.voteVisible = true
      })
    },
    del(index, row) {
      let _this = this
      _this.$axios.get(`admin/vote/delete?voteId=${row.voteId}`).then((resp) => {
        if (resp.data.flag) {
          _this.$message.success(resp.data.message)
          _this.list()
        } else {
          _this.$message.error(resp.data.message)
        }
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
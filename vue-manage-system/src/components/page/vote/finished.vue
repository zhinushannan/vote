<template>
  <section class="main">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item><i class="el-icon-rank"></i> 投票管理</el-breadcrumb-item>
        <el-breadcrumb-item>已结束投票</el-breadcrumb-item>
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
        <el-table-column label="操作" width="300" align="center">
          <template slot-scope="scope">
            <el-button
                type="text"
                icon="el-icon-edit"
                @click="handleEdit(scope.$index, scope.row)"
            >查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </section>
</template>

<script>
export default {
  name: "finished",
  data() {
    return {
      page: {
        page: 1,
        size: 10,
        total: 0,
        data: []
      },
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
    stopVote(index, row) {
      let _this = this
      _this.$axios.get(`admin/vote/stop?voteId=${row.voteId}`).then((resp) => {
        if (resp.data.flag) {
          _this.$message.success("停止成功")
          _this.list()
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
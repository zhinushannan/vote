<template>

  <el-container>
    <el-header>实干青年工作室投票系统</el-header>
    <el-main>
      <header>
        <h1>{{ vote.name }}</h1>
        <span>{{ vote.description }}</span>
      </header>
      <main>
        <el-row>
          <el-col :span="1"><div style="height: 1px; width: 1px;"></div></el-col>
          <el-col :span="22">
            <el-row :gutter="20">
              <el-col :span="12"  v-for="item in vote.candidates">
                <el-card :body-style="{ padding: '0px' }">
                  <el-image
                      style="width: 100%; height: 15vh"
                      :src="item.imgUrl"
                      :preview-src-list="[item.imgUrl]"
                      fit="cover">
                  </el-image>
                  <div style="padding: 2px;">
                    <el-button @click="openDrawer(item)">{{ item.name }}</el-button>
                  </div>
                </el-card>
              </el-col>
            </el-row>

            <el-button @click="score">打分</el-button>

          </el-col>
          <el-col :span="1"></el-col>
        </el-row>

      </main>

    </el-main>


    <el-drawer
        :title="drawerData.name"
        :visible.sync="drawer"
        :direction="direction"
        size="60%">
      <div style="padding: 10px">
        <div v-html="drawerData.abstractOfCandidate"></div>
        <el-divider></el-divider>
        <div v-html="drawerData.introductionHtml"></div>
        <el-divider></el-divider>
        <el-rate
            v-model="vote['scores'][drawerData.candidateId]"
            show-score
            :max=10
            text-color="#ff9900"
            score-template="{value}"
            style="display: inline-block; margin-right: 10px;"
        >
        </el-rate>
      </div>
    </el-drawer>


    <el-dialog
        :visible.sync="dialogVisible"
        width="50%"
        :before-close="handleClose"
        :show-close=false
    >
      <el-result icon="warning" title="当前投票不存在或已结束！">
      </el-result>
    </el-dialog>

  </el-container>

</template>

<script>
export default {
  name: "page",
  data() {
    return {
      vote: {
        scores: {}
      },
      drawer: false,
      direction: 'ttb',
      drawerData: {},
      poll: {},
      dialogVisible: false
    }
  },
  methods: {
    openDrawer(item) {
      this.drawerData = item
      if (!this.vote['scores'][this.drawerData.candidateId]) {
        this.vote['scores'][this.drawerData.candidateId] = 0
      }
      this.drawer = true;
    },
    score() {
      let _this = this
      let data = {
        voteId: _this.vote.voteId,
        scores: _this.vote.scores
      }
      _this.$axios.post("user/poll/do", data).then((resp) => {
        console.log(resp)
      })
    },
    handleClose() {
      return
    }
  },
  mounted() {
    let _this = this

    _this.$axios.get(`user/vote/get?voteId=${_this.$route.path.split('/')[3]}`).then((resp) => {
      if (resp.data.code === 403) {
        _this.dialogVisible = true
        return
      }
      _this.vote = resp.data.data
    })

  }
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #E9EEF3;
  text-align: center;
}
</style>
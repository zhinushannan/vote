package club.kwcoder.vote.dataobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CandidateDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CandidateDOExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCandidateIdIsNull() {
            addCriterion("candidate_id is null");
            return (Criteria) this;
        }

        public Criteria andCandidateIdIsNotNull() {
            addCriterion("candidate_id is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateIdEqualTo(Integer value) {
            addCriterion("candidate_id =", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdNotEqualTo(Integer value) {
            addCriterion("candidate_id <>", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdGreaterThan(Integer value) {
            addCriterion("candidate_id >", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("candidate_id >=", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdLessThan(Integer value) {
            addCriterion("candidate_id <", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdLessThanOrEqualTo(Integer value) {
            addCriterion("candidate_id <=", value, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdIn(List<Integer> values) {
            addCriterion("candidate_id in", values, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdNotIn(List<Integer> values) {
            addCriterion("candidate_id not in", values, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdBetween(Integer value1, Integer value2) {
            addCriterion("candidate_id between", value1, value2, "candidateId");
            return (Criteria) this;
        }

        public Criteria andCandidateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("candidate_id not between", value1, value2, "candidateId");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andCandidateNameIsNull() {
            addCriterion("candidate_name is null");
            return (Criteria) this;
        }

        public Criteria andCandidateNameIsNotNull() {
            addCriterion("candidate_name is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateNameEqualTo(String value) {
            addCriterion("candidate_name =", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameNotEqualTo(String value) {
            addCriterion("candidate_name <>", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameGreaterThan(String value) {
            addCriterion("candidate_name >", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameGreaterThanOrEqualTo(String value) {
            addCriterion("candidate_name >=", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameLessThan(String value) {
            addCriterion("candidate_name <", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameLessThanOrEqualTo(String value) {
            addCriterion("candidate_name <=", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameLike(String value) {
            addCriterion("candidate_name like", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameNotLike(String value) {
            addCriterion("candidate_name not like", value, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameIn(List<String> values) {
            addCriterion("candidate_name in", values, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameNotIn(List<String> values) {
            addCriterion("candidate_name not in", values, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameBetween(String value1, String value2) {
            addCriterion("candidate_name between", value1, value2, "candidateName");
            return (Criteria) this;
        }

        public Criteria andCandidateNameNotBetween(String value1, String value2) {
            addCriterion("candidate_name not between", value1, value2, "candidateName");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdIsNull() {
            addCriterion("introduction_md is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdIsNotNull() {
            addCriterion("introduction_md is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdEqualTo(String value) {
            addCriterion("introduction_md =", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdNotEqualTo(String value) {
            addCriterion("introduction_md <>", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdGreaterThan(String value) {
            addCriterion("introduction_md >", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdGreaterThanOrEqualTo(String value) {
            addCriterion("introduction_md >=", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdLessThan(String value) {
            addCriterion("introduction_md <", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdLessThanOrEqualTo(String value) {
            addCriterion("introduction_md <=", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdLike(String value) {
            addCriterion("introduction_md like", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdNotLike(String value) {
            addCriterion("introduction_md not like", value, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdIn(List<String> values) {
            addCriterion("introduction_md in", values, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdNotIn(List<String> values) {
            addCriterion("introduction_md not in", values, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdBetween(String value1, String value2) {
            addCriterion("introduction_md between", value1, value2, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionMdNotBetween(String value1, String value2) {
            addCriterion("introduction_md not between", value1, value2, "introductionMd");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlIsNull() {
            addCriterion("introduction_html is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlIsNotNull() {
            addCriterion("introduction_html is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlEqualTo(String value) {
            addCriterion("introduction_html =", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlNotEqualTo(String value) {
            addCriterion("introduction_html <>", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlGreaterThan(String value) {
            addCriterion("introduction_html >", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlGreaterThanOrEqualTo(String value) {
            addCriterion("introduction_html >=", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlLessThan(String value) {
            addCriterion("introduction_html <", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlLessThanOrEqualTo(String value) {
            addCriterion("introduction_html <=", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlLike(String value) {
            addCriterion("introduction_html like", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlNotLike(String value) {
            addCriterion("introduction_html not like", value, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlIn(List<String> values) {
            addCriterion("introduction_html in", values, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlNotIn(List<String> values) {
            addCriterion("introduction_html not in", values, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlBetween(String value1, String value2) {
            addCriterion("introduction_html between", value1, value2, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andIntroductionHtmlNotBetween(String value1, String value2) {
            addCriterion("introduction_html not between", value1, value2, "introductionHtml");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampIsNull() {
            addCriterion("create_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampIsNotNull() {
            addCriterion("create_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampEqualTo(Date value) {
            addCriterion("create_timestamp =", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampNotEqualTo(Date value) {
            addCriterion("create_timestamp <>", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampGreaterThan(Date value) {
            addCriterion("create_timestamp >", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("create_timestamp >=", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampLessThan(Date value) {
            addCriterion("create_timestamp <", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampLessThanOrEqualTo(Date value) {
            addCriterion("create_timestamp <=", value, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampIn(List<Date> values) {
            addCriterion("create_timestamp in", values, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampNotIn(List<Date> values) {
            addCriterion("create_timestamp not in", values, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampBetween(Date value1, Date value2) {
            addCriterion("create_timestamp between", value1, value2, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andCreateTimestampNotBetween(Date value1, Date value2) {
            addCriterion("create_timestamp not between", value1, value2, "createTimestamp");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentIsNull() {
            addCriterion("version_current is null");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentIsNotNull() {
            addCriterion("version_current is not null");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentEqualTo(Integer value) {
            addCriterion("version_current =", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotEqualTo(Integer value) {
            addCriterion("version_current <>", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentGreaterThan(Integer value) {
            addCriterion("version_current >", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_current >=", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentLessThan(Integer value) {
            addCriterion("version_current <", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentLessThanOrEqualTo(Integer value) {
            addCriterion("version_current <=", value, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentIn(List<Integer> values) {
            addCriterion("version_current in", values, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotIn(List<Integer> values) {
            addCriterion("version_current not in", values, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentBetween(Integer value1, Integer value2) {
            addCriterion("version_current between", value1, value2, "versionCurrent");
            return (Criteria) this;
        }

        public Criteria andVersionCurrentNotBetween(Integer value1, Integer value2) {
            addCriterion("version_current not between", value1, value2, "versionCurrent");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
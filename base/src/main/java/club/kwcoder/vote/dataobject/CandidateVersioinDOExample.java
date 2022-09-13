package club.kwcoder.vote.dataobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CandidateVersioinDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CandidateVersioinDOExample() {
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

        public Criteria andCandidateAbstractIsNull() {
            addCriterion("candidate_abstract is null");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractIsNotNull() {
            addCriterion("candidate_abstract is not null");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractEqualTo(String value) {
            addCriterion("candidate_abstract =", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractNotEqualTo(String value) {
            addCriterion("candidate_abstract <>", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractGreaterThan(String value) {
            addCriterion("candidate_abstract >", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractGreaterThanOrEqualTo(String value) {
            addCriterion("candidate_abstract >=", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractLessThan(String value) {
            addCriterion("candidate_abstract <", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractLessThanOrEqualTo(String value) {
            addCriterion("candidate_abstract <=", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractLike(String value) {
            addCriterion("candidate_abstract like", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractNotLike(String value) {
            addCriterion("candidate_abstract not like", value, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractIn(List<String> values) {
            addCriterion("candidate_abstract in", values, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractNotIn(List<String> values) {
            addCriterion("candidate_abstract not in", values, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractBetween(String value1, String value2) {
            addCriterion("candidate_abstract between", value1, value2, "candidateAbstract");
            return (Criteria) this;
        }

        public Criteria andCandidateAbstractNotBetween(String value1, String value2) {
            addCriterion("candidate_abstract not between", value1, value2, "candidateAbstract");
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

        public Criteria andModifyTimestampIsNull() {
            addCriterion("modify_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampIsNotNull() {
            addCriterion("modify_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampEqualTo(Date value) {
            addCriterion("modify_timestamp =", value, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampNotEqualTo(Date value) {
            addCriterion("modify_timestamp <>", value, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampGreaterThan(Date value) {
            addCriterion("modify_timestamp >", value, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_timestamp >=", value, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampLessThan(Date value) {
            addCriterion("modify_timestamp <", value, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampLessThanOrEqualTo(Date value) {
            addCriterion("modify_timestamp <=", value, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampIn(List<Date> values) {
            addCriterion("modify_timestamp in", values, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampNotIn(List<Date> values) {
            addCriterion("modify_timestamp not in", values, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampBetween(Date value1, Date value2) {
            addCriterion("modify_timestamp between", value1, value2, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andModifyTimestampNotBetween(Date value1, Date value2) {
            addCriterion("modify_timestamp not between", value1, value2, "modifyTimestamp");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNull() {
            addCriterion("version_id is null");
            return (Criteria) this;
        }

        public Criteria andVersionIdIsNotNull() {
            addCriterion("version_id is not null");
            return (Criteria) this;
        }

        public Criteria andVersionIdEqualTo(Integer value) {
            addCriterion("version_id =", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotEqualTo(Integer value) {
            addCriterion("version_id <>", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThan(Integer value) {
            addCriterion("version_id >", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("version_id >=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThan(Integer value) {
            addCriterion("version_id <", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdLessThanOrEqualTo(Integer value) {
            addCriterion("version_id <=", value, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdIn(List<Integer> values) {
            addCriterion("version_id in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotIn(List<Integer> values) {
            addCriterion("version_id not in", values, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdBetween(Integer value1, Integer value2) {
            addCriterion("version_id between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("version_id not between", value1, value2, "versionId");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionIsNull() {
            addCriterion("version_description is null");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionIsNotNull() {
            addCriterion("version_description is not null");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionEqualTo(String value) {
            addCriterion("version_description =", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionNotEqualTo(String value) {
            addCriterion("version_description <>", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionGreaterThan(String value) {
            addCriterion("version_description >", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("version_description >=", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionLessThan(String value) {
            addCriterion("version_description <", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionLessThanOrEqualTo(String value) {
            addCriterion("version_description <=", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionLike(String value) {
            addCriterion("version_description like", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionNotLike(String value) {
            addCriterion("version_description not like", value, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionIn(List<String> values) {
            addCriterion("version_description in", values, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionNotIn(List<String> values) {
            addCriterion("version_description not in", values, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionBetween(String value1, String value2) {
            addCriterion("version_description between", value1, value2, "versionDescription");
            return (Criteria) this;
        }

        public Criteria andVersionDescriptionNotBetween(String value1, String value2) {
            addCriterion("version_description not between", value1, value2, "versionDescription");
            return (Criteria) this;
        }
    }

    /**
     *
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
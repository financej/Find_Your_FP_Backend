package com.metlife.team09.domain.openai.enums;

public enum ConversationType {

	SUMMARY("상대방이 보내는 텍스트는 고객과 메트라이프 생명보험 설계사와의 대화내용입니다. \n" +
			"그리고 다음의 내용을 응답메시지에 반드시 포함해야합니다. 각 내용은 한줄 정도로 간단 명료하게 요약되어야 합니다.\n" +
			"첫번째, 고객이 어떤 설계 고민을 가지고 있는지 요약.\n" +
			"두번째, 설계사가 답변한 전반적인 내용 요약\n" +
			"세번째,  결과적으로 고객이 어떤 보험을 선택하였는지 내용 요약\n" +
			"네번째, 고객이 최종적으로 선택한 보험에 대한 자세한 내용 포함\n" +
			"\n" +
			"위 내용은 고객에게 요약해서 보낼 것입니다. 따라서, 고객이 읽었을 때 이질감이 없는 어투로 응답바랍니다. 예를 들면 고객은 연금보험에 대해 문의하며...(X) 고객님께선 연금보험에 대해 문의하며...(O)처럼 '고객'을 지칭하는 용어를 '고객님께선'으로 통일해주세요.. 반드시!!! \n" +
			"설계사를 지칭하는 용어는 '설계사는'으로 통일해주세요!\n" +
			"또한, 각 요약한 문장은 글머리 기호로 정리해주세요.\n");

	private final String requestContent;

	ConversationType(String requestContent) {
		this.requestContent = requestContent;
	}

	public String getRequestContent() {
		return this.requestContent;
	}
}

package spring.library.domain.enums;

// enum 으로는 완성은 나중에 수정하기
public enum MemberFeature {
	STUDENT("학생"),ADMIN("관리자");
	MemberFeature(String feature){this.feature=feature;}

	private final String feature;
}

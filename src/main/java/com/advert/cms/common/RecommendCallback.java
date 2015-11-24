package com.advert.cms.common;

import java.util.List;

public interface RecommendCallback<E> {

	public List<E> findByIds(List<Long> ids);

	public RecommendDto transform(E o);

	public static class RecommendDto {
		private Long id;

		private String name;

		public RecommendDto() {
		}

		public RecommendDto(Long id, String name) {
			this.id = id;
			this.name = name;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}

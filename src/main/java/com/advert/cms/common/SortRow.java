package com.advert.cms.common;

import org.apache.commons.lang3.StringUtils;

public class SortRow {

	private static final Sort[] EMPTY_SORT = new Sort[0];

	private Sort[] sorts = EMPTY_SORT;

	/**
	 * sortValue : 1,1;2,3;
	 * 
	 * @param sortValue
	 */
	public SortRow(String sortValue) {
		String[] values = StringUtils.split(sortValue, ';');
		if (values != null && values.length > 0) {
			sorts = new Sort[values.length];
			for (int i = 0; i < values.length; i++) {
				sorts[i] = new Sort(values[i]);
			}
		}
	}

	public Sort[] getSorts() {
		return sorts;
	}

	public static class Sort {
		public final Long id;
		public final Integer sortNo;

		private Sort(String value) {
			String[] values = StringUtils.split(value, ',');
			this.id = Long.valueOf(values[0]);
			this.sortNo = Integer.valueOf(values[1]);
		}
	}
}

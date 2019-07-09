package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sorter.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffSet();
	Integer getLimit();
	Sorter getSorter();
}

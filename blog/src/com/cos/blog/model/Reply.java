package com.cos.blog.model;

import com.sun.jmx.snmp.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
	private int id;
	private int userid;
	private int boardid;
	private String content;
	private Timestamp createDate;
}

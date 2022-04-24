package br.com.wohr.projectservicereader.domain;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUser {

	private List<User> data;

}

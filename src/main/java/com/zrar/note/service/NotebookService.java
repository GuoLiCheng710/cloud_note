package com.zrar.note.service;

import java.util.List;
import java.util.Map;

import com.zrar.note.exception.NotFoundUserException;

public interface NotebookService {
	List<Map<String, Object>> listNotebook(String userId) throws NotFoundUserException;
}

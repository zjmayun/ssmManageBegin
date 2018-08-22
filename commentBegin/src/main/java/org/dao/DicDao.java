package org.dao;

import java.util.List;

import org.bean.Dic;

public interface DicDao {
    List<Dic> selectByListType(Dic dic);
}

package org.service;

import java.util.List;

import org.bean.Menu;
import org.dto.MenuDto;
import org.dto.MenuForMoveDto;
import org.dto.MenuForZtreeDto;

public interface MenuService {
    List<MenuForZtreeDto>  selectMenuWithAction(Menu menu); 
    
    boolean add(MenuDto menuDto);
    
    boolean delete(Integer id);
    
    MenuDto select(Integer id);
    
    boolean modify(MenuDto menuDto);
    
    boolean sortMenu(MenuForMoveDto moveDto);
}

package com.valts.ob_forum_demo.servicios;

import com.valts.ob_forum_demo.models.Role;

public interface RoleService {
    Role findByName(String name);

}

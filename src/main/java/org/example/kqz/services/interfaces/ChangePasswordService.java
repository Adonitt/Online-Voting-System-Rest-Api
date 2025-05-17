package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.user.ChangePasswordDto;

public interface ChangePasswordService {
    void changePassword(ChangePasswordDto request, String userEmail);
}

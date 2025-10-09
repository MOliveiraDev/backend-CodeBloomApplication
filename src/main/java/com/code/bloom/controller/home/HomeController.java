package com.code.bloom.controller.home;

import com.code.bloom.dto.account.MyAccountResponse;
import com.code.bloom.service.home.MyAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/v1/home")
@RequiredArgsConstructor
public class HomeController {

    private final MyAccountService myAccountService;

    @GetMapping("/perfil")
    public ResponseEntity<MyAccountResponse> getAccount() throws AccessDeniedException {
        return ResponseEntity.ok(myAccountService.getMyProfile());
    }

}

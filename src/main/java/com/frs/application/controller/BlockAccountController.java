package com.frs.application.controller;

import com.frs.application.payload.request.blockAccount.BlockAccountCreateRequest;
import com.frs.application.payload.response.BlockAccountResponse;
import com.frs.application.payload.response.IngredientsResponse;
import com.frs.application.service.IBlockAccountService;
import com.frs.application.service.impl.BlockAccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blockAccount")
@RequiredArgsConstructor
public class BlockAccountController {

    private final IBlockAccountService iBlockAccountService;

    @GetMapping("/{id}")
    public ResponseEntity<List<BlockAccountResponse>> getByAccountID(@PathVariable Long id) {
        return ResponseEntity.ok(iBlockAccountService.getByAccountID(id));
    }

    @PostMapping("/{block_account_id}")
    public ResponseEntity<BlockAccountResponse> create(@PathVariable Long blockAccountId, HttpServletRequest req) {
        return ResponseEntity.ok(iBlockAccountService.create(blockAccountId, req.getRemoteUser()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        iBlockAccountService.delete(id);
        return ResponseEntity.ok().build();
    }
}

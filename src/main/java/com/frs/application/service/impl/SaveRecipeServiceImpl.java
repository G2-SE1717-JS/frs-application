package com.frs.application.service.impl;

import com.frs.application.dto.AccountDTO;
import com.frs.application.dto.RecipeDTO;
import com.frs.application.dto.SaveRecipeDTO;
import com.frs.application.logic.IAccountLogic;
import com.frs.application.logic.IRecipeLogic;
import com.frs.application.logic.ISaveRecipeLogic;
import com.frs.application.payload.response.SaveRecipeResponse;
import com.frs.application.service.IAccountService;
import com.frs.application.service.ISaveRecipeService;
import com.frs.core.exceptions.SystemBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.frs.core.helpers.MessageHelper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaveRecipeServiceImpl implements ISaveRecipeService {
    private final ISaveRecipeLogic saveRecipeLogic;
    private final IAccountLogic accountLogic;
    private final IRecipeLogic recipeLogic;


    @Override
    public List<SaveRecipeResponse> getByAccountID(Long accountId) {
        List<SaveRecipeDTO> saveRecipeDTOS = saveRecipeLogic.getAllSaveRecipe(accountId);
        return saveRecipeDTOS.stream().map(
                saveRecipeDTO -> SaveRecipeResponse.builder()
                        .id(saveRecipeDTO.getId())
                        .recipeId(saveRecipeDTO.getRecipeId())
                        .accountId(saveRecipeDTO.getAccountId())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public SaveRecipeResponse save(Long recipeId, String remoteUser) {

        AccountDTO accountDTO = accountLogic.findByUsername(remoteUser);

        SaveRecipeDTO savedRecipeDTO = SaveRecipeDTO.builder()
                .recipeId(accountDTO.getId())
                .accountId(accountDTO.getId())
                .build();


        return SaveRecipeResponse.builder()
                .id(savedRecipeDTO.getId())
                .recipeId(savedRecipeDTO.getRecipeId())
                .accountId(savedRecipeDTO.getAccountId())
                .build();
    }

    @Override
    public void delete(Long id) {
        SaveRecipeDTO savedRecipeDTO = saveRecipeLogic.getById(id);
        if (Objects.isNull(savedRecipeDTO))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.save_recipe.alr-existed"));
        savedRecipeDTO.setDeleted(true);
        saveRecipeLogic.save(savedRecipeDTO);
    }

}

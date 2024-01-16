package com.frs.application.service.impl;

import com.frs.application.dto.FollowAccountDTO;
import com.frs.application.logic.IFollowAccountLogic;
import com.frs.application.payload.request.followaccount.FollowAccountCreateRequest;
import com.frs.application.payload.request.followaccount.FollowAccountDeleteRequest;
import com.frs.application.payload.response.FollowAccountResponse;
import com.frs.application.service.IFollowAccountService;
import com.frs.core.exceptions.SystemBadRequestException;
import com.frs.core.helpers.MessageHelper;
import com.frs.core.utils.VersionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowAccountServiceImpl implements IFollowAccountService {
    private final IFollowAccountLogic followAccountLogic;
    @Override
    public List<FollowAccountResponse> getAll() {
        List<FollowAccountDTO> followAccountDTOS = followAccountLogic.getAllFollow();
        return followAccountDTOS.stream().map(
                FollowAccountDTO -> FollowAccountResponse.builder()
                        .id(FollowAccountDTO.getId())
                        .accountID(FollowAccountDTO.getAccountID())
                        .followedAccountID(FollowAccountDTO.getFollowedAccountID())
                        .createdDate(FollowAccountDTO.getCreatedDate())
                        .lastModifiedDate(FollowAccountDTO.getLastModifiedDate())
                        .build()
        ).collect(Collectors.toList());
    }
    @Override
    public FollowAccountResponse create(FollowAccountCreateRequest request) {
        FollowAccountDTO followAccountDTOS = FollowAccountDTO.builder()
                .followedAccountID(request.getFollowAccountID())
                .build();
        followAccountDTOS = followAccountLogic.save(followAccountDTOS);
        return FollowAccountResponse.builder()
                .id(followAccountDTOS.getId())
                .accountID(followAccountDTOS.getAccountID())
                .followedAccountID(followAccountDTOS.getFollowedAccountID())
                .createdDate(followAccountDTOS.getCreatedDate())
                .lastModifiedDate(followAccountDTOS.getLastModifiedDate())
                .build();
    }

    @Override
    public FollowAccountResponse getById(Long id) {
        FollowAccountDTO followAccountDTOS = followAccountLogic.getById(id);
        if (Objects.isNull(followAccountDTOS)) {
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.followAccount.not-existed"));
        }
        return FollowAccountResponse.builder()
                .id(followAccountDTOS.getId())
                .accountID(followAccountDTOS.getAccountID())
                .followedAccountID(followAccountDTOS.getFollowedAccountID())
                .createdDate(followAccountDTOS.getCreatedDate())
                .lastModifiedDate(followAccountDTOS.getLastModifiedDate())
                .build();
    }

/*   @Override
    public IngredientsResponse update(Long id, IngredientsUpdateRequest request) {
        IngredientsDTO ingredientsDTO = ingredientsLogic.getById(id);
        if (Objects.isNull(ingredientsDTO))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.ingredients.not-existed"));
        ingredientsDTO = IngredientsDTO.builder()
                .id(ingredientsDTO.getId())
                .name(request.getName())
                .image(request.getImage())
                .createdDate(ingredientsDTO.getCreatedDate())
                .build();
        ingredientsDTO=ingredientsLogic.save(ingredientsDTO);
        return IngredientsResponse.builder()
                .id(ingredientsDTO.getId())
                .name(ingredientsDTO.getName())
                .image(ingredientsDTO.getImage())
                .createdDate(ingredientsDTO.getCreatedDate())
                .lastModifiedDate(ingredientsDTO.getLastModifiedDate())
                .build();
    }
 */

    @Override
    public FollowAccountResponse delete(FollowAccountDeleteRequest request) {
        FollowAccountDTO followAccountDTOS = FollowAccountDTO.builder()
                .followedAccountID(request.getFollowAccountID())
                .build();
        followAccountDTOS = followAccountLogic.save(followAccountDTOS);
        return FollowAccountResponse.builder()
                .id(followAccountDTOS.getId())
                .accountID(followAccountDTOS.getAccountID())
                .followedAccountID(followAccountDTOS.getFollowedAccountID())
                .createdDate(followAccountDTOS.getCreatedDate())
                .lastModifiedDate(followAccountDTOS.getLastModifiedDate())
                .build();
    }
/*    @Override
    public void delete(Long id) {
        IngredientsDTO ingredientsDTO = ingredientsLogic.getById(id);
        if (Objects.isNull(ingredientsDTO ))
            throw new SystemBadRequestException(MessageHelper.getMessage("validation.service.not-existed"));

        ingredientsDTO.setDeleted(true);
        ingredientsLogic.save(ingredientsDTO);
    }

 */
}

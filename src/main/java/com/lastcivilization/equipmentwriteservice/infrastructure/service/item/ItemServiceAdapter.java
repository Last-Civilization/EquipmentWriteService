package com.lastcivilization.equipmentwriteservice.infrastructure.service.item;

import com.lastcivilization.equipmentwriteservice.domain.exception.ApplicationException;
import com.lastcivilization.equipmentwriteservice.domain.port.ItemService;
import com.lastcivilization.equipmentwriteservice.domain.port.dto.ItemDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.lastcivilization.equipmentwriteservice.infrastructure.service.item.ItemMapper.MAPPER;

@Service
@RequiredArgsConstructor
class ItemServiceAdapter implements ItemService {

    private final ItemClient itemClient;
    @Override
    public Optional<ItemDto> getItem(long id) {
        Optional<ItemDto> itemDtoOptional;
        try{
            Item item = itemClient.getItem(id);
            itemDtoOptional = Optional.of(item)
                    .map(MAPPER::toDto);
        } catch (FeignException exception){
            if(exception.status() == 404){
                itemDtoOptional = Optional.empty();
            } else {
                throw new ApplicationException(exception);
            }
        }
        return itemDtoOptional;
    }

    @Override
    public boolean isItem(long id) {
        try{
            return itemClient.isItem(id);
        } catch (FeignException exception){
            throw new ApplicationException(exception);
        }
    }
}

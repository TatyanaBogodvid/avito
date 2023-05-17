package ru.skypro.homework.service.mapper;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;

import java.util.Collection;

public interface AdMapper {
    AdsDto mapAdToAdDto(Ad ad);
    Ad mapAdsDtoToAd(AdsDto adsDto);
    FullAdsDto mapAdToFullAdsDTo(Ad ad);
    Ad mapCreatedAdsDtoToAd(CreateAdsDto createAdsDto);
    Collection<AdsDto> mapAdListToAdDtoList(Collection<Ad> adCollection);

}

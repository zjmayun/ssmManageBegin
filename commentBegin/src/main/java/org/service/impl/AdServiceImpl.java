package org.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bean.Ad;
import org.dao.AdDao;
import org.dto.AdDto;
import org.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.util.FileUtil;

@Service
public class AdServiceImpl implements AdService{

	@Value("${adImage.savePath}")
	private String adImageSavePath;

	@Value("${adImage.url}")
	private String adImageUrl;
	
	@Autowired
	private AdDao adDao;
	
	@Override
	public List<AdDto> selectByPage(AdDto adDto) {
        List<AdDto> list=new ArrayList<AdDto>();
        Ad ad=new Ad();
        BeanUtils.copyProperties(adDto, ad);
        List<Ad> listAd=adDao.selectListByPage(ad);
        for(Ad adOne:listAd) {
        	AdDto adDtoOne=new AdDto();
        	BeanUtils.copyProperties(adOne,adDtoOne);
        	adDtoOne.setImg(adImageUrl+adOne.getImgFileName());
        	list.add(adDtoOne);
        }
		return list;
	}

	@Override
	public AdDto selectById(int id) {
		Ad ad=adDao.selectById(id);
		AdDto adDto=new AdDto();
		BeanUtils.copyProperties(ad, adDto);
		adDto.setImg(adImageUrl+ad.getImgFileName());
		return adDto;
	}

	@Override
	public boolean delete(int id) {
		Ad ad=adDao.selectById(id);
		File file=new File(adImageSavePath+ad.getImgFileName());
		if(file.exists()) {
			file.delete();
		}
		int i=adDao.delete(id);
		return i==1;
	}

	@Override
	public boolean update(AdDto adDto) {
        Ad ad=new Ad();
        BeanUtils.copyProperties(adDto, ad);
        if(adDto.getFile()!=null&&adDto.getFile().getSize()>0) {
           String fileName=System.currentTimeMillis()+"-"+adDto.getFile().getOriginalFilename();
           File file=FileUtil.getFile(fileName, adImageSavePath);
           try {
        	Ad adD=adDao.selectById(adDto.getId());
			adDto.getFile().transferTo(file);
			ad.setImgFileName(fileName);
			int i=adDao.update(ad);
			File fileD=new File(adImageSavePath+adD.getImgFileName());
			if(fileD.exists()) {
				fileD.delete();
			}
			return i==1;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
           
        }
        int i=adDao.update(ad);
		return i==1;
	}

	@Override
	public boolean add(AdDto adDto) {
		Ad ad=new Ad();
		BeanUtils.copyProperties(adDto, ad);
		if(adDto.getFile()!=null&&adDto.getFile().getSize()>0) {
			String fileName=System.currentTimeMillis()+"-"+adDto.getFile().getOriginalFilename();
			File file=new File(adImageSavePath+fileName);
			if(file.isDirectory()) {
                 file.mkdirs();				
			}
			try {
				adDto.getFile().transferTo(file);
				ad.setImgFileName(fileName);
				int i=adDao.insert(ad);
				return i==1;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			return false;
		}
		return false;
	}

}

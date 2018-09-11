package org.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bean.Ad;
import org.bean.Business;
import org.bean.Page;
import org.dao.AdDao;
import org.dao.BusinessDao;
import org.dto.AdDto;
import org.dto.BusinessDto;
import org.dto.BusinessListDto;
import org.service.AdService;
import org.service.BusinessService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.util.FileUtil;

@Service
public class BusinessServiceImpl implements BusinessService{

	@Value("${businessImage.savePath}")
	private String businessSavePath;

	@Value("${businessImage.url}")
	private String businessImageUrl;

	@Autowired
	private BusinessDao businessDao;
	
	@Override
	public List<BusinessDto> selectByPage(BusinessDto BusDto) {
		    List<BusinessDto> list=new ArrayList<BusinessDto>();
	        Business bus=new Business();
	        BeanUtils.copyProperties(BusDto, bus);
	        List<Business> listBusiness=businessDao.selectByPage(bus);
	        for(Business business:listBusiness) {
	        	BusinessDto busDtoO=new BusinessDto();
	        	BeanUtils.copyProperties(business, busDtoO);
	        	busDtoO.setImg(businessImageUrl+business.getImgFileName());
	        	list.add(busDtoO);
	        }
			return list;
	}

	@Override
	public BusinessDto selectById(int id) {
        Business business=businessDao.selectById(id);
        BusinessDto businessDto=new BusinessDto();
        BeanUtils.copyProperties(business, businessDto);
        businessDto.setImg(businessImageUrl+business.getImgFileName());
        businessDto.setStar(this.getStar(businessDto));
		return businessDto;
	}

	@Override
	public boolean delete(int id) {
		Business business=businessDao.selectById(id);
		File file=new File(businessSavePath+business.getImgFileName());
		if(file.exists()) {
			file.delete();
		}
        int i=businessDao.delete(id);
		return i==1;
	}

	@Override
	public boolean update(BusinessDto businessDto) {
		Business business=new Business();
		BeanUtils.copyProperties(businessDto,business);
		if(businessDto.getFile()!=null&&businessDto.getFile().getSize()>0) {
			Business bus=businessDao.selectById(business.getId());
			String fileName=System.currentTimeMillis()+"-"+businessDto.getFile().getOriginalFilename();
			File file=new File(businessSavePath+fileName);
			if(!file.isDirectory()) {
				file.mkdirs();
			}
			try {
				String originalName=bus.getImgFileName();
				businessDto.getFile().transferTo(file);
				business.setImgFileName(fileName);
				File fileO=new File(businessSavePath+originalName);
				if(fileO.exists()) {
					fileO.delete();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int i=businessDao.update(business);
		return i==1;
	}

	@Override
	public boolean add(BusinessDto businessDto) {
		Business business=new Business();
		BeanUtils.copyProperties(businessDto,business);
		if(businessDto.getFile()!=null&&businessDto.getFile().getSize()>0) {
			 String fileName=System.currentTimeMillis()+"-"+businessDto.getFile().getOriginalFilename();
			 File file=new File(businessSavePath);
			 if(!file.isDirectory()) {
				 file.mkdirs();
			 }
			 try {
				businessDto.getFile().transferTo(new File(businessSavePath+fileName));
				business.setImgFileName(fileName);
				business.setNumber(0);
				business.setCommentTotalNum(0);
				business.setStarTotalNum(0);
				int i=businessDao.add(business);
				return i==1;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public BusinessListDto selectByPageForApi(BusinessDto businessDto) {
		BusinessListDto btolist=new BusinessListDto();
		Business business=new Business();
		BeanUtils.copyProperties(businessDto, business);
        if(businessDto.getKeyword()!=null) {
        	 business.setTitle(businessDto.getKeyword());
        	 business.setDesc(businessDto.getKeyword());
        	 business.setSubTitle(businessDto.getKeyword());
        }
        //前端页码从0开始的，这里需要进行加1
        Page page=business.getPage();
        business.getPage().setCurrentPage(page.getCurrentPage()+1);
        List<Business> list=businessDao.selectLikeByPage(business);
        List<BusinessDto> listdto=new ArrayList<BusinessDto>();
        btolist.setData(listdto);
        btolist.setHasMore(page.getCurrentPage()<page.getTotalPage());
        for(Business bu:list) {
        	BusinessDto bto=new BusinessDto();
        	BeanUtils.copyProperties(bu, bto);
        	bto.setMumber(bu.getNumber());
        	bto.setImg(businessImageUrl+bu.getImgFileName());
            listdto.add(bto);
        }
		return btolist;
	}
	
	public int getStar(BusinessDto businessDto) {
		if(businessDto.getStarTotalNum()!=null&&businessDto.getCommentTotalNum()!=null&&businessDto.getCommentTotalNum()>0) {
			return (int)(businessDto.getStarTotalNum()/businessDto.getCommentTotalNum());
		}
		return 0;
	}
	
	

}

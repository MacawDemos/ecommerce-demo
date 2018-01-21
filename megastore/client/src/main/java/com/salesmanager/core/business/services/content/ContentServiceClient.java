package com.salesmanager.core.business.services.content;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.macaw.Megastore;
import com.salesmanager.core.model.content.Content;
import com.salesmanager.core.model.content.ContentDescription;
import com.salesmanager.core.model.content.ContentType;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import io.macaw.test.impl.ServiceConfig;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceClient implements ContentService {
  private Megastore megastore;

  public ContentServiceClient() {
    megastore = ServiceConfig.getInstance().locateService(Megastore.class, "com.salesmanager.core.business.services", "megastore");
  }

  public List listByType(List contentType, MerchantStore store, Language language) throws
      ServiceException {
    return megastore.contentListByType(contentType,store,language);
  }

  public List listByType(ContentType contentType, MerchantStore store, Language language) throws
      ServiceException {
    return megastore.contentListByType1(contentType,store,language);
  }

  public List listByType(List contentType, MerchantStore store) throws ServiceException {
    return megastore.contentListByType2(contentType,store);
  }

  public Content getByLanguage(Long id, Language language) throws ServiceException {
    return megastore.contentGetByLanguage(id,language);
  }

  public List listNameByType(List contentType, MerchantStore store, Language language) throws
      ServiceException {
    return megastore.contentListNameByType(contentType,store,language);
  }

  public Content getByCode(String code, MerchantStore store) throws ServiceException {
    return megastore.contentGetByCode(code,store);
  }

  public Content getByCode(String code, MerchantStore store, Language language) throws
      ServiceException {
    return megastore.contentGetByCode1(code,store,language);
  }

  public Content getById(Long id) {
    return megastore.contentGetById(id);
  }

  public void saveOrUpdate(Content content) throws ServiceException {
    try {
        Object out = megastore.contentSaveOrUpdate(content);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(content, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void addContentFile(String merchantStoreCode, InputContentFile contentFile) throws
      ServiceException {
    megastore.contentAddContentFile(merchantStoreCode,contentFile);
  }

  public void addLogo(String merchantStoreCode, InputContentFile cmsContentImage) throws
      ServiceException {
    megastore.contentAddLogo(merchantStoreCode,cmsContentImage);
  }

  public void addOptionImage(String merchantStoreCode, InputContentFile cmsContentImage) throws
      ServiceException {
    megastore.contentAddOptionImage(merchantStoreCode,cmsContentImage);
  }

  public void addContentFiles(String merchantStoreCode, List contentFilesList) throws
      ServiceException {
    megastore.contentAddContentFiles(merchantStoreCode,contentFilesList);
  }

  public void removeFile(String merchantStoreCode, FileContentType fileContentType, String fileName)
      throws ServiceException {
    megastore.contentRemoveFile(merchantStoreCode,fileContentType,fileName);
  }

  public void removeFiles(String merchantStoreCode) throws ServiceException {
    megastore.contentRemoveFiles(merchantStoreCode);
  }

  public OutputContentFile getContentFile(String merchantStoreCode, FileContentType fileContentType,
      String fileName) throws ServiceException {
    return megastore.contentGetContentFile(merchantStoreCode,fileContentType,fileName);
  }

  public List getContentFiles(String merchantStoreCode, FileContentType fileContentType) throws
      ServiceException {
    return megastore.contentGetContentFiles(merchantStoreCode,fileContentType);
  }

  public List getContentFilesNames(String merchantStoreCode, FileContentType fileContentType) throws
      ServiceException {
    return megastore.contentGetContentFilesNames(merchantStoreCode,fileContentType);
  }

  public ContentDescription getBySeUrl(MerchantStore store, String seUrl) {
    return megastore.contentGetBySeUrl(store,seUrl);
  }

  public void delete(Content content) throws ServiceException {
    megastore.contentDelete1(content);
  }

  public Long count() {
    return megastore.contentCount();
  }

  public void update(Content entity) throws ServiceException {
    try {
        Object out = megastore.contentUpdate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void create(Content entity) throws ServiceException {
    try {
        Object out = megastore.contentCreate(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public List list() {
    return megastore.contentList();
  }

  public void save(Content entity) throws ServiceException {
    try {
        Object out = megastore.contentSave(entity);
        if (out != null)  org.apache.commons.beanutils.PropertyUtils.copyProperties(entity, out);
    }
    catch (Exception e) {
        throw new RuntimeException(e);
    }
  }

  public void flush() {
    megastore.contentFlush();
  }
}

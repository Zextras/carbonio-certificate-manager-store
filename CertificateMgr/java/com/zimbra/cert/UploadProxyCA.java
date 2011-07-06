

package com.zimbra.cert;

import java.io.IOException;
import java.util.Map;

import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.AdminConstants;
import com.zimbra.common.soap.CertMgrConstants;
import com.zimbra.common.soap.Element;
import com.zimbra.common.util.ByteUtil;
import com.zimbra.common.util.ZimbraLog;
import com.zimbra.cs.service.FileUploadServlet;
import com.zimbra.cs.service.FileUploadServlet.Upload;
import com.zimbra.cs.service.admin.AdminDocumentHandler;
import com.zimbra.soap.ZimbraSoapContext;

public class UploadProxyCA extends AdminDocumentHandler {
    private final static String CERT_AID = "cert.aid" ;
    private final static String CERT_NAME = "cert.filename" ;

   	public Element handle(Element request, Map<String, Object> context) throws ServiceException {
   		ZimbraSoapContext lc = getZimbraSoapContext(context);
        Element response = lc.createElement(CertMgrConstants.UPLOAD_PROXYCA_RESPONSE);

        String attachId = null;
        String filename = null;
        Upload up = null ;

		try {
            attachId = request.getAttribute(CERT_AID) ;
            filename = request.getAttribute(CERT_NAME) ;
            ZimbraLog.security.debug("Found certificate Filename  = " + filename + "; attid = " + attachId );

            up = FileUploadServlet.fetchUpload(lc.getAuthtokenAccountId(), attachId, lc.getAuthToken());
            if (up == null)
                throw ServiceException.FAILURE("Uploaded file " + filename + " with " + attachId + " was not found.", null);

            byte [] blob = ByteUtil.getContent(up.getInputStream(),-1) ;
            if(blob.length > 0)
                response.addAttribute("cert_content", new String(blob));
		}catch (IOException ioe) {
			throw ServiceException.FAILURE("Can not get uploaded certificate content", ioe);
		}finally {
            FileUploadServlet.deleteUpload(up);
        }

        return response;


   	}
}

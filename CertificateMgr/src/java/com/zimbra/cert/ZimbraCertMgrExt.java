/*
 * ***** BEGIN LICENSE BLOCK *****
 * Zimbra Collaboration Suite Server
 * Copyright (C) 2007, 2008, 2009, 2010, 2013, 2014 Zimbra, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software Foundation,
 * version 2 of the License.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 * ***** END LICENSE BLOCK *****
 */
package com.zimbra.cert;

import com.zimbra.common.service.ServiceException;
import com.zimbra.cs.extension.ZimbraExtension;
import com.zimbra.soap.SoapServlet;


public class ZimbraCertMgrExt implements ZimbraExtension {
    public static final String EXTENSION_NAME_CERTMGR = "com_zimbra_cert_manager";
    
    //Remote commands
    public static final String GET_STAGED_CERT_CMD = "zmcertmgr viewstagedcrt" ;
    public static final String GET_DEPLOYED_CERT_CMD = "zmcertmgr viewdeployedcrt" ;
    public static final String CREATE_CSR_CMD = "zmcertmgr createcsr" ;
    public static final String CREATE_CRT_CMD = "zmcertmgr createcrt"   ;
    public static final String DEPLOY_CERT_CMD = "zmcertmgr deploycrt" ;
    public static final String GET_CSR_CMD = "zmcertmgr viewcsr" ;
    public static final String VERIFY_CRTKEY_CMD = "zmcertmgr verifycrtkey" ;
    public static final String VERIFY_COMM_CRTKEY_CMD = "zmcertmgr verifycrt" ;
    public static final String VERIFY_CRTCHAIN_CMD = "zmcertmgr verifycrtchain" ;
    // put vefified certificate and key to tempoaray dir
    public static final String COMM_CRT_KEY_FILE_NAME = "commercial.key";
    public static final String COMM_CRT_FILE_NAME = "commercial.crt";
    public static final String COMM_CRT_CA_FILE_NAME = "commercial_ca.crt";

    public static final String ALL_SERVERS = "--- All Servers ---" ;
    public static final String A_zimbraSSLPrivateKey = "zimbraSSLPrivateKey" ;
    public static final String A_zimbraSSLCertificate = "zimbraSSLCertificate" ;
    public void destroy() {
    }

    public String getName() {
        return EXTENSION_NAME_CERTMGR ;
    }

    public void init() throws ServiceException {
        SoapServlet.addService("AdminServlet", new ZimbraCertMgrService());
    }
}
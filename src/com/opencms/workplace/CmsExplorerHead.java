package com.opencms.workplace;

/*
 * File   : $Source: /alkacon/cvs/opencms/src/com/opencms/workplace/Attic/CmsExplorerHead.java,v $
 * Date   : $Date: 2000/10/16 09:45:45 $
 * Version: $Revision: 1.21 $
 *
 * Copyright (C) 2000  The OpenCms Group 
 * 
 * This File is part of OpenCms -
 * the Open Source Content Mananagement System
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * For further information about OpenCms, please see the
 * OpenCms Website: http://www.opencms.com
 * 
 * You should have received a copy of the GNU General Public License
 * long with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

import com.opencms.file.*;
import com.opencms.core.*;
import com.opencms.util.*;
import com.opencms.template.*;

import javax.servlet.http.*;

import java.util.*;

/**
 * Template class for displaying the explorer head of the OpenCms workplace.<P>
 * Reads template files of the content type <code>CmsXmlWpTemplateFile</code>.
 * 
 * @author Michael Emmerich
 * @version $Revision: 1.21 $ $Date: 2000/10/16 09:45:45 $
 */
public class CmsExplorerHead extends CmsWorkplaceDefault implements I_CmsWpConstants,
																	 I_CmsConstants {
	   /** Definition of the Datablock PARENT */   
	 private final static String C_PARENT="PARENT";
	 
	 /** Definition of the Datablock PARENT_ENABLED */   
	 private final static String C_PARENT_ENABLED="PARENT_ENABLED";    

	 /** Definition of the Datablock PARENT_DISABLED */   
	 private final static String C_PARENT_DISABLED="PARENT_DISABLED";    
  
	 /** Definition of the Datablock PREVIOUS */   
	 private final static String C_PREVIOUS="PREVIOUS";
	 
	 /** Definition of the Datablock PREVIOUS_ENABLED */   
	 private final static String C_PREVIOUS_ENABLED="PREVIOUS_ENABLED";    

	 /** Definition of the Datablock PREVIOUS_DISABLED */   
	 private final static String C_PREVIOUS_DISABLED="PREVIOUS_DISABLED"; 
	 
	 /** Definition of the Datablock NEW */   
	 private final static String C_NEW="NEW";
	 
	 /** Definition of the Datablock NEW_ENABLED */   
	 private final static String C_NEW_ENABLED="NEW_ENABLED";    

	 /** Definition of the Datablock NEW_DISABLED */   
	 private final static String C_NEW_DISABLED="NEW_DISABLED"; 
	 
	 
	 /** Definition of the Datablock FILELIST */   
	 private final static String C_FILELIST="FILELIST";
	 
	 /** Definition of the Datablock PREVIOUSLIST */   
	 private final static String C_PREVIOUSLIST="PREVIOUSLIST";

	 /** Definition of the Datablock STARTUP */   
	 private final static String C_STARTUP="STARTUP";
	
	 /** Definition of the Datablock STARTUP_FILE */   
	 private final static String C_STARTUP_FILE="STARTUP_FILE";
	 
	 /** Definition of the Datablock STARTUP_FOLDER */   
	 private final static String C_STARTUP_FOLDER="STARTUP_FOLDER";
	 
	 /** Definition of the Datablock  LINK_VALUE */
	 private final static String C_LINK_VALUE="LINK_VALUE";   

 	 private final static String C_SITECOPY="SITECOPY";	 
	 private final static String C_SITECOPY_ENABLED="SITECOPY_ENABLED";    
	 private final static String C_SITECOPY_DISABLED="SITECOPY_DISABLED"; 
	
/**
 * Overwrties the getContent method of the CmsWorkplaceDefault.<br>
 * The explorer head works in three different modes that are selected by 
 * different parameters in the request:
 * <ul>
 * <li>Normal mode: No parameter is given, in this mode address input field and the
 * navigation buttons are displayed. </li>
 * <li>View mode: This mode is used wehn the "viewfile" parameter is set. It is used
 * when a file is displayed in the main explorer window and only includes a back button 
 * in the explorer head. </li>
 * <li>URL mode: This mode is activated when a file or folder is given in the address input
 * field. It is used to determine the file or fodler to be displayed. </li>
 * </ul>
 *
 * @param cms The CmsObject.
 * @param templateFile The login template file
 * @param elementName not used
 * @param parameters Parameters of the request and the template.
 * @param templateSelector Selector of the template tag to be displayed.
 * @return Bytearre containgine the processed data of the template.
 * @exception Throws CmsException if something goes wrong.
 */
public byte[] getContent(CmsObject cms, String templateFile, String elementName, Hashtable parameters, String templateSelector) throws CmsException
{
	String viewfile = null;
	String filelist = null;
	String previous = null;
	String url = null;
	String currentFilelist = null;
	;
	String previousFilelist = null;
	String newFilelist = null;

	// the template to be displayed
	String template = null;
	// TODO: check, if this is neede: Hashtable preferences=new Hashtable();

	// get session and servlet root
	I_CmsSession session = cms.getRequestContext().getSession(true);
	String servlets = ((HttpServletRequest) cms.getRequestContext().getRequest().getOriginalRequest()).getServletPath();
	CmsXmlWpTemplateFile xmlTemplateDocument = new CmsXmlWpTemplateFile(cms, templateFile);

	// if the viewfile value is included in the request, exchange the explorer
	// read with a back button to the file list
	viewfile = (String) parameters.get(C_PARA_VIEWFILE);
	if (viewfile != null)
	{
		template = "viewfile";
	}
	else
	{

		// Check if the URL parameter was included in the request. It is set when
		// a folder or file is entered in the address input field.
		url = (String) parameters.get(C_PARA_URL);
		if (url == null)
		{
			xmlTemplateDocument.clearStartup();
		}
		else
		{

			// check if the requested url is a file or a folder.
			if (url.endsWith("/"))
			{
				// the url is a folder, so prepare to update the file list and tree
				xmlTemplateDocument.setData(C_FILELIST, url);
				xmlTemplateDocument.setData(C_STARTUP, xmlTemplateDocument.getProcessedDataValue(C_STARTUP_FOLDER, this));
				currentFilelist = (String) session.getValue(C_PARA_FILELIST);
				if (currentFilelist == null)
				{
					currentFilelist = cms.rootFolder().getAbsolutePath();
				}
				session.putValue(C_PARA_PREVIOUSLIST, currentFilelist);
				session.putValue(C_PARA_FILELIST, url);
				session.putValue(C_PARA_FOLDER, url);
			}
			else
			{
				// the url is a file, so show the requested document
				xmlTemplateDocument.setData(C_LINK_VALUE, servlets + url);
				xmlTemplateDocument.setData(C_STARTUP, xmlTemplateDocument.getProcessedDataValue(C_STARTUP_FILE, this));
				// the url is a file
			}
		}

		// check if a previous filelist parameter was included in the request.
		// if a previous filelist was included, overwrite the value in the session for later use.
		previous = (String) parameters.get(C_PARA_PREVIOUSLIST);
		if (previous != null)
		{
			session.putValue(C_PARA_PREVIOUSLIST, previous);
		}

		// get the previous current filelist to calculate the link for the back button.
		previousFilelist = (String) session.getValue(C_PARA_PREVIOUSLIST);

		//check if a filelist  parameter was included in the request.
		//if a filelist was included, overwrite the value in the session for later use.
		filelist = cms.getRequestContext().getRequest().getParameter(C_PARA_FILELIST);
		if (filelist != null)
		{
			session.putValue(C_PARA_FILELIST, filelist);
		}

		// get the current filelist to calculate its patent
		currentFilelist = (String) session.getValue(C_PARA_FILELIST);
		// if no filelist parameter was given, use the current folder
		if (currentFilelist == null)
		{
			currentFilelist = cms.rootFolder().getAbsolutePath();
		}
		if (!currentFilelist.equals("/"))
		{
			// cut off last "/"
			newFilelist = currentFilelist.substring(0, currentFilelist.length() - 1);
			// now get the partent folder
			int end = newFilelist.lastIndexOf("/");
			if (end > -1)
			{
				newFilelist = newFilelist.substring(0, end + 1);
			}
		}
		else
		{
			newFilelist = currentFilelist;
		}

		// put the refereences to the filelist and the previous filelist into the
		// template.
		xmlTemplateDocument.setData(C_FILELIST, newFilelist);
		xmlTemplateDocument.setData(C_PREVIOUSLIST, previousFilelist);

		// update the value for the back link.
		// this is required that for the explorer head after the back link is used.
		// session.putValue(C_PARA_PREVIOUSLIST,currentFilelist);

		// set the parent button to enabled if not on the root folder
		if (currentFilelist.equals("/"))
		{
			xmlTemplateDocument.setData(C_PARENT, xmlTemplateDocument.getProcessedDataValue(C_PARENT_DISABLED, this));
		}
		else
		{
			xmlTemplateDocument.setData(C_PARENT, xmlTemplateDocument.getProcessedDataValue(C_PARENT_ENABLED, this));
		}

		// set the parent button to enabled if not on the root folder
		if (previousFilelist == null)
		{
			xmlTemplateDocument.setData(C_PREVIOUS, xmlTemplateDocument.getProcessedDataValue(C_PREVIOUS_DISABLED, this));
		}
		else
		{
			xmlTemplateDocument.setData(C_PREVIOUS, xmlTemplateDocument.getProcessedDataValue(C_PREVIOUS_ENABLED, this));
		}

		// check if the new resource button must be enabeld.
		// this is only done if the project is not the online project.
		if (cms.getRequestContext().currentProject().equals(cms.onlineProject()))
		{
			xmlTemplateDocument.setData(C_NEW, xmlTemplateDocument.getProcessedDataValue(C_NEW_DISABLED, this));
			xmlTemplateDocument.setData(C_SITECOPY, xmlTemplateDocument.getProcessedDataValue(C_SITECOPY_DISABLED, this));
		}
		else
		{
			xmlTemplateDocument.setData(C_NEW, xmlTemplateDocument.getProcessedDataValue(C_NEW_ENABLED, this));
			xmlTemplateDocument.setData(C_SITECOPY, xmlTemplateDocument.getProcessedDataValue(C_SITECOPY_ENABLED, this));
		}
	}
	// process the selected template
	return startProcessing(cms, xmlTemplateDocument, "", parameters, template);
}
	/**
	 * Indicates if the results of this class are cacheable.
	 * 
	 * @param cms CmsObject Object for accessing system resources
	 * @param templateFile Filename of the template file 
	 * @param elementName Element name of this template in our parent template.
	 * @param parameters Hashtable with all template class parameters.
	 * @param templateSelector template section that should be processed.
	 * @return <EM>true</EM> if cacheable, <EM>false</EM> otherwise.
	 */
	public boolean isCacheable(CmsObject cms, String templateFile, String elementName, Hashtable parameters, String templateSelector) {
		return false;
	}
	/**
	 * Sets the value of the address input filed of the file header.
	 * This method is directly called by the content definiton.
	 * @param Cms The CmsObject.
	 * @param lang The language file.
	 * @param parameters User parameters.
	 * @return Value that is set into the adress field.
	 * @exception CmsExeption if something goes wrong.
	 */
	public String setValue(CmsObject cms, CmsXmlLanguageFile lang, Hashtable parameters)
		throws CmsException {
		I_CmsSession session= cms.getRequestContext().getSession(true);

		// get the current filelist to display it in the address input field.
		String currentFilelist=(String)session.getValue(C_PARA_FILELIST);
		// if no filelist parameter was given, use the current folder
		if (currentFilelist==null) {
			 currentFilelist=cms.rootFolder().getAbsolutePath();
		} 
	  return currentFilelist;
	}
}

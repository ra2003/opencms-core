/*
 * File   : $Source: /alkacon/cvs/opencms/src/org/opencms/jsp/util/I_CmsJspDeviceSelector.java,v $
 * Date   : $Date: 2009/12/15 15:24:39 $
 * Version: $Revision: 1.1 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2009 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.jsp.util;

import javax.servlet.http.HttpServletRequest;

/**
 * This interface supports the basic functionality for the device detection.<p> 
 * 
 * @author Ruediger Kurz
 * @since 7.9.3
 * @version 1.0
 */
public interface I_CmsJspDeviceSelector {

    /** Request parameter to force device selection. */
    String REQUEST_ATTRIBUTE_DEVICE = "__device";

    /**
     * Returns the name of the device type.<p>
     * 
     * This will be one option in {@link #getDeviceTypes()}.<p>
     * 
     * @param req the HTTP servlet request to process
     * 
     * @return the name of the device type
     */
    public String getDeviceType(HttpServletRequest req);

    /**
     * Returns an array of device types that can be detected.<p>
     * 
     * @return an array of device types that can be detected
     */
    public String[] getDeviceTypes();
}

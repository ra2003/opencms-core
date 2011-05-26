/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/gwt/client/property/Attic/CmsReloadMode.java,v $
 * Date   : $Date: 2011/05/26 08:26:40 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2011 Alkacon Software (http://www.alkacon.com)
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

package org.opencms.gwt.client.property;

/**
 * An enum specifying whose values specify whether an entry or its parent need to be reloaded.<p>
 * 
 * @author Georg Westenberger
 * 
 * @version $Revision 1.0$
 * 
 * @since 8.0.0
 */
public enum CmsReloadMode {
    /** This value means that neither the entry nor its parent should be reloaded. */
    none,
    /** This value means the entry should be reloaded. */
    reloadEntry,
    /** This value means the entry's parent should be reloaded. */
    reloadParent;
}

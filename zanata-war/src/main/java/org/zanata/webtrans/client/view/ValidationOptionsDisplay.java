/*
 * Copyright 2012, Red Hat, Inc. and individual contributors as indicated by the
 * @author tags. See the copyright.txt file in the distribution for a full
 * listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF
 * site: http://www.fsf.org.
 */

package org.zanata.webtrans.client.view;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.zanata.common.LocaleId;
import org.zanata.webtrans.shared.model.DocumentId;
import org.zanata.webtrans.shared.model.TransUnitValidationResult;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public interface ValidationOptionsDisplay extends WidgetDisplay
{
   HasValueChangeHandlers<Boolean> addValidationSelector(String label, String tooltip, boolean enabled, boolean locked);

   void changeValidationSelectorValue(String label, boolean enabled);

   void clearValidationSelector();
   
   void setRunValidationVisible(boolean visible);

   void setListener(Listener listener);

   interface Listener
   {
      void onRunValidation();

      void onRequestValidationReport();
   }

   void setRunValidationTitle(String title);

   void updateValidationResult(Date endTime);

   void showReportLink(boolean visible);

   void updateDocValidationReport(DocumentId documentId, LocaleId localeId, List<TransUnitValidationResult> result, Date endTime);

   void initValidationReport(Set<DocumentId> errorDocs);
}

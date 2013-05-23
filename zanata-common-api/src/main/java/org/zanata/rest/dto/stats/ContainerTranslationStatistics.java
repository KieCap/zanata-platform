/*
 * Copyright 2010, Red Hat, Inc. and individual contributors as indicated by the
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
package org.zanata.rest.dto.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.zanata.common.CommonContainerTranslationStatistics;
import org.zanata.common.TranslationStatistics;
import org.zanata.rest.dto.Link;
import org.zanata.rest.dto.Links;

/**
 * Generic Container for translation statistics.
 * @author Carlos Munoz <a href="mailto:camunoz@redhat.com">camunoz@redhat.com</a>
 */
@XmlType(name = "containerTranslationStatisticsType", propOrder = {"refs", "stats", "detailedStats"})
@XmlRootElement(name = "containerStats")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder( { "id", "refs", "stats", "detailedStats" })
public class ContainerTranslationStatistics extends CommonContainerTranslationStatistics implements Serializable
{
   private static final long serialVersionUID = 1L;
   private String id;
   private Links refs;
   private List<ContainerTranslationStatistics> detailedStats;

   public ContainerTranslationStatistics()
   {
   }

   /**
    * Identifier for the container (i.e. Project, Project Iteration, Document, etc).
    */
   @XmlAttribute
   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   /**
    * References to related elements (i.e. Reference to the container).
    */
   @XmlElementWrapper(name = "refs")
   @XmlElement(name = "containerRef")
   public Links getRefs()
   {
      return refs;
   }

   public void setRefs(Links refs)
   {
      this.refs = refs;
   }
   
   /**
    * Actual translation statistics.
    */
   @XmlElementWrapper(name = "stats")
   @XmlElement(name = "stat")
   @Override
   public List<TranslationStatistics> getStats()
   {
      return super.getStats();
   }

   /**
    * Detailed Statistics if so requested.
    */
   @XmlElementWrapper(name = "detailedStats")
   @XmlElement(name = "containerStats")
   public List<ContainerTranslationStatistics> getDetailedStats()
   {
      return detailedStats;
   }

   public void setDetailedStats(List<ContainerTranslationStatistics> detailedStats)
   {
      this.detailedStats = detailedStats;
   }

   public void addRef( Link newRef )
   {
      if( this.refs == null )
      {
         this.refs = new Links();
      }
      this.refs.add( newRef );
   }

   public void addDetailedStats( ContainerTranslationStatistics newDetailedStats )
   {
      if( this.detailedStats == null )
      {
         this.detailedStats = new ArrayList<ContainerTranslationStatistics>();
      }
      this.detailedStats.add(newDetailedStats);
   }
}
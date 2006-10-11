/**
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.tuscany.samples.sdo.specCodeSnippets;

import org.apache.tuscany.samples.sdo.SdoSampleConstants;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLHelper;
import commonj.sdo.helper.XSDHelper;

/**
 * Demonstrats accessing a created DataObject's properties using xPath.
 * 
 * This sample is from the <a href="http://incubator.apache.org/tuscany"
 * target="_blank"> Apache Tuscany</a> project. It was written to help users
 * understand and experiement with SDO. It is based upon code snipets contained
 * within, and is meant for use with, and reference to the <a
 * href="http://download.boulder.ibm.com/ibmdl/pub/software/dw/specs/ws-sdo/SDO_Specification_Java_V2.01.pdf"
 * target="_bank">SDO Specification</a>. <br>
 * <br>
 * It is worth noting that the Examples section of the specification, and the
 * {@link org.apache.tuscany.samples.sdo.specExampleSection.AccessingDataObjectsUsingXPath}
 * use unpure xpath expression. This sample attempts to demonstrate a more appropiate
 * subset of xPath operations.<br>
 * <br>
 * <b>Usage:</b> <br>
 * Include the following on your classpath :
 * codegen-2.2.0-SNAPSHOT.jar,codegen-ecore-2.2.0-SNAPSHOT.jar,common-2.2.0-SNAPSHOT.jar,ecore-2.2.0-SNAPSHOT.jar,ecore-change-2.2.0-SNAPSHOT.jar,ecore-xmi-2.2.0-SNAPSHOT.jar,sdo-api-SNAPSHOT.jar,tuscany-sdo-impl-SNAPSHOT.jar,xsd-2.2.0-SNAPSHOT.jar,sdo-samples-standAlone-1.0-SNAPSHOT.jar
 * <br>
 * Execute: java
 * org.apache.tuscany.samples.sdo.specCodeSnippets.AccessDataObjectUsingValidXPath
 * 
 * @author Robbie Minshall
 */
public class AccessDataObjectUsingValidXPath {

    /**
     * Prints a subset of item properties to System.out where the individual item was
     * accessed using an xPath expression
     * 
     * @param purchaseOrder.
     *            DataObject defined by Types in
     *            {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XSD_RESOURCE}
     */
    public static void accessDataObjectUsingXPath(DataObject purchaseOrder) {

        System.out.println("Accessing individual item from list using xpath");
        // TODO: use variety of xpath expressions such as items/item[1]
        // TODO: add to junit test cases for test cases above
        DataObject item = purchaseOrder.getDataObject("items/item[1]");
        System.out.println("Item toString : " + item.toString());

        System.out.println("Item name:" + item.get("productName"));
        System.out.println("Part num: " + item.get("partNum"));

    }

    /**
     * Accesses and modifies properties of a purchase order DataObject using xPath(
     * properties are defined in the xsd
     * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XSD_RESOURCE} and
     * populated by xml
     * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XML_RESOURCE} )
     * 
     * @param args.
     *            No parameters required.
     */
    public static void main(String[] args) {

        // information
        System.out.println("***************************************");
        System.out.println("SDO Sample AccessDataObjectUsingValidXPath");
        System.out.println("***************************************");
        System.out.println("Demonstrats accessing a created DataObject's properties using xPath.");
        System.out.println("***************************************");

        // create a DataObejct
        DataObject purchaseOrder = null;
        try {
            XSDHelper.INSTANCE.define(ClassLoader.getSystemResourceAsStream(SdoSampleConstants.PO_XSD_RESOURCE), null);
            purchaseOrder = XMLHelper.INSTANCE.load(ClassLoader.getSystemResourceAsStream(SdoSampleConstants.PO_XML_RESOURCE)).getRootObject();
            System.out.println("DataObject created");
        } catch (Exception e) {
            System.out.println("Error creating DataObject " + e.toString());
            e.printStackTrace();
            return;
        }

        // start of sample
        try {

            accessDataObjectUsingXPath(purchaseOrder);
        } catch (Exception e) {
            System.out.println("Sorry there was an error accessing properties by name " + e.toString());
            e.printStackTrace();
        }
        System.out.println("GoodBye");
        // end of sample
    }

}
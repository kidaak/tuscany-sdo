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
 * Demonstrates accessing a created DataObject's properties using xPath.
 * 
 * This sample is from the <a href="http://incubator.apache.org/tuscany" target="_blank"> Apache Tuscany</a> project. It was written to help users
 * understand and experiement with SDO. It is based upon code snippets contained within, and is meant for use with, and reference to the <a
 * href="http://www.osoa.org/download/attachments/791/SDO_Specification_Java_V2.01.pdf?version=1" target="_bank">SDO Specification</a>.
 * <br>
 * <br>
 * It is worth noting that the Examples section of the specification, and the
 * {@link org.apache.tuscany.samples.sdo.specExampleSection.AccessingDataObjectsUsingXPath} use unpure xpath expression. This sample attempts to
 * demonstrate a more appropiate subset of xPath operations.<br>
 * <br>
 * <b>Usage:</b> <br>
 * This sample can easily be run from within Eclipse as a Java Application if tuscany or the sample-sdo project is imported into Eclipse as an
 * existing project. <br>
 * <br>
 * If executing as a standalone application please do the following: <br>
 * <UL>
 * <LI>Include the following jar files on your classpath :
 * <UL>
 * <LI>SDO API and Tuscany Implementation
 * <UL>
 * <LI>sdo-api-{version}.jar - SDO API
 * <LI>tuscany-sdo-impl-{version}.jar - Tuscany SDO implementation
 * </UL>
 * </LI>
 * <LI>EMF dependencies.
 * <UL>
 * <LI>emf-common-{version}.jar - some common framework utility and base classes
 * <LI>emf-ecore-{version}.jar - the EMF core runtime implementation classes (the Ecore metamodel)
 * <LI>emf-ecore-change-{version}.jar - the EMF change recorder and framework
 * <LI>emf-ecore-xmi-{version}.jar - EMF's default XML (and XMI) serializer and loader
 * <LI>xsd-{version}.jar - the XML Schema model
 * </UL>
 * </LI>
 * </UL>
 * 
 * These jar files can be obtained from directly from Tuscany and EMF projects or from <a
 * href="http://wiki.apache.org/ws-data/attachments/Tuscany(2f)TuscanyJava(2f)SDO_Java_Overview/attachments/SDO%20Execution%20Dependencies"
 * target="_bank">SDO Execution Dependancies </a> </LI>
 * <LI>Execute: <br>
 * java org.apache.tuscany.samples.sdo.specCodeSnippets.UsingXPath</LI>
 * </UL>
 * 
 * @author Robbie Minshall
 */
public class UsingXPath {

    /**
     * Prints a subset of item properties to System.out where the individual item was accessed using an xPath expression
     * 
     * @param purchaseOrder.
     *            DataObject defined by Types in {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XSD_RESOURCE}
     */
    public static void accessDataObjectUsingXPath(DataObject purchaseOrder) {
        // TODO: use variety of xpath expressions such as items/item[1]
        // TODO: add to junit test cases for test cases above

        // xpath expressions for obtaining various items
        String[] xPathExpressions = { "items/item[1]","items/item[productName=Baby Monitor]" };

        for (int i = 0; i < xPathExpressions.length; i++) {
            try {
                System.out.println("Accessing individual item from list using xpath expression" + xPathExpressions[i]);

                DataObject item = purchaseOrder.getDataObject(xPathExpressions[i]);
                System.out.println("    DataObject toString : " + item.toString());

                System.out.println("    Item name:" + item.get("productName"));
                System.out.println("    Part num: " + item.get("partNum"));
            } catch (Exception e) {
                System.out.println("    Sorry there was an error executing expression " + xPathExpressions[i]);
            }
        }
        
        //TODO:  select various lists of properties from DataObjects using xPath.
    }

    /**
     * Accesses and modifies properties of a purchase order DataObject using xPath( properties are defined in the xsd
     * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XSD_RESOURCE} and populated by xml
     * {@link org.apache.tuscany.samples.sdo.SdoSampleConstants#PO_XML_RESOURCE} )
     * 
     * @param args.
     *            No parameters required.
     */
    public static void main(String[] args) {

        // information
        System.out.println("***************************************");
        System.out.println("SDO Sample UsingXPath");
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
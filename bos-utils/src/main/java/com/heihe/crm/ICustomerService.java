
package com.heihe.crm;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ICustomerService", targetNamespace = "http://service.heihe.com/")
@XmlSeeAlso({
})
public interface ICustomerService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findDecidedzoneIdByAddress", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindDecidedzoneIdByAddress")
    @ResponseWrapper(localName = "findDecidedzoneIdByAddressResponse", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindDecidedzoneIdByAddressResponse")
    public String findDecidedzoneIdByAddress(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.heihe.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListHasAssociation", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindListHasAssociation")
    @ResponseWrapper(localName = "findListHasAssociationResponse", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindListHasAssociationResponse")
    public List<Customer> findListHasAssociation(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.heihe.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.heihe.crm.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTelephone", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindCustomerByTelephone")
    @ResponseWrapper(localName = "findCustomerByTelephoneResponse", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindCustomerByTelephoneResponse")
    public Customer findCustomerByTelephone(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "assigncustomerstodecidedzone", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.Assigncustomerstodecidedzone")
    @ResponseWrapper(localName = "assigncustomerstodecidedzoneResponse", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.AssigncustomerstodecidedzoneResponse")
    public void assigncustomerstodecidedzone(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    List<Integer> arg1);

    /**
     * 
     * @return
     *     returns java.util.List<com.heihe.crm.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findListNotAssociation", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindListNotAssociation")
    @ResponseWrapper(localName = "findListNotAssociationResponse", targetNamespace = "http://service.heihe.com/", className = "com.heihe.crm.FindListNotAssociationResponse")
    public List<Customer> findListNotAssociation();

}
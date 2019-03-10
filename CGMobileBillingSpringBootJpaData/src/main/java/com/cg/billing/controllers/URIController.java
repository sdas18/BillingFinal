package com.cg.billing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.services.BillingServices;
@Controller
public class URIController {
	@Autowired
	BillingServices billingServices;
	private Plan plan;
	private Customer customer;
	private PostpaidAccount postpaidAccount;
	@RequestMapping("/")
	public String getIndexPage() {
		return "indexPage";
	}
	
	@RequestMapping("/registration")
    public String getRegistrationPage() {
        return "registrationPage";
    }
    @RequestMapping("/openPostpaidAccount")
    public String getPostpaidAccountPage() {
        return "postpaidAccountPage";
    }
    @RequestMapping("/billgeneration")
    public String getgenerateBillPage() {
        return "generateBillPage";
    }
    @RequestMapping("/getCustomerDetails")
    public String getCustomerDetails() {
        return "customerDetailsPage";
    }
    @RequestMapping("/getpostpaidAccountDetails")
    public String getpostpaidAccountDetails() {
        return "postpaidAccountDetailsPage";
    }
    @RequestMapping("/getMonthlyBill")
    public String getMonthlyBillPage() {
        return "monthlyBillPage";
    }
    
    @RequestMapping("/getAccountAllBill")
    public String getAccountAllBills() {
        return "AccountAllBillPage";
    }
    @RequestMapping("/changePlan")
    public ModelAndView getchangePlanPage() {
    	List<Plan>plans;
    	plans=billingServices.getAllPlanDetails();
        return  new ModelAndView("changePlanPage", "plans", plans);
    }
    @RequestMapping("/getCustomerAllPostpaidAccountDetails")
    public String getCustomerAllPostpaidAccountDetailsPage() {
        return "customerAllPostpaidAccountPage";
    } 
    @RequestMapping("/closeAccount")
    public String getCloseAccountPage() {
        return "closeAccountPage";
    }
    @RequestMapping("/deleteCustomerDetails")
    public String getdeleteCustomerPage() {
        return "deleteCustomerPage";
    }
    @ModelAttribute
    public Customer getCustomer() {
     customer = new Customer();
    return customer;
    }
    @ModelAttribute
    public PostpaidAccount getPostpaidAccount() {
     postpaidAccount = new PostpaidAccount();
    return postpaidAccount;
    }
}

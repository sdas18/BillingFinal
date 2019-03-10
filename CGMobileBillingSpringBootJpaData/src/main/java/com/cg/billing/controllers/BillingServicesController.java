package com.cg.billing.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
import com.cg.billing.services.BillingServices;

@Controller
public class BillingServicesController {
	@Autowired
	BillingServices billingServices;

	@RequestMapping("/registrationCustomer")
	public ModelAndView registerAssociate(@ModelAttribute Customer customer) {
		customer=billingServices.acceptCustomerDetails(customer);
		return new ModelAndView("registrationSuccessPage","customer",customer);
	}
	@RequestMapping("/postpaidAccountOpen")
	public ModelAndView postpaidAccountOpen(@RequestParam int customerId,int planId) throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
		long mobileNumber=billingServices.openPostpaidMobileAccount(customerId,planId);
		return new ModelAndView("postpaidAccountPage","mobileNumber",mobileNumber);
	}
	@RequestMapping("/generateBill")
	public ModelAndView generateBill(@RequestParam int customerId,long mobileNumber,String billMonth,int noOfLocalSMS,int noOfStdSMS,int noOfLocalCalls,int noOfStdCalls,int internetDataUsageUnits) throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException {
		double bill=billingServices.generateMonthlyMobileBill(customerId,mobileNumber,billMonth,noOfLocalSMS,noOfStdSMS,noOfLocalCalls,noOfStdCalls,internetDataUsageUnits);
		return new ModelAndView("generateBillPage","bill",bill);
	}
	@RequestMapping("/customerDetails")
	public ModelAndView customerDetails(@RequestParam int customerId) throws CustomerDetailsNotFoundException {
		Customer customer=billingServices.getCustomerDetails(customerId);
		return new ModelAndView("customerDetailsPage","customer",customer);
	}
	@RequestMapping("/postpaidAccountDetail")
	public ModelAndView postpaidAccountDetail(@RequestParam int customerId,long mobileNumber) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		PostpaidAccount postpaidAccount=billingServices.getPostPaidAccountDetails(customerId,mobileNumber);
		return new ModelAndView("postpaidAccountDetailsPage","postpaidAccount",postpaidAccount);
	}
	@RequestMapping("/monthlyBill")
	public ModelAndView billDetails(@RequestParam int customerId,long mobileNumber,String month) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException {
		Bill bill=billingServices.getMobileBillDetails(customerId,mobileNumber,month);
		return new ModelAndView("monthlyBillPage","bill",bill);
	}
	@RequestMapping("/changeMobilePlan")
	public ModelAndView changeMobilePlan(@RequestParam int customerId,long mobileNumber,int planId) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException,PlanDetailsNotFoundException {
		billingServices. changePlan(customerId,mobileNumber,planId);
		return new ModelAndView("changePlanPage","success","Your plan has been changed successfully");
	}
	
	@RequestMapping("/customerAllAccount")
	public ModelAndView changeMobilePlan(@RequestParam int customerId) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException,PlanDetailsNotFoundException {
		List<PostpaidAccount>accounts=billingServices.getCustomerAllPostpaidAccountsDetails(customerId);
		return new ModelAndView("customerAllPostpaidAccountPage","accounts",accounts);
	}
	
	@RequestMapping("/accountAllBills")
	public ModelAndView accountAllbills(@RequestParam int customerId,long mobileNo) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException,PlanDetailsNotFoundException {
		List<Bill>bills=billingServices.getCustomerPostPaidAccountAllBillDetails(customerId, mobileNo);
		return new ModelAndView("customerAllPostpaidAccountPage","bills",bills);
	}
	
	@RequestMapping("/closePostpaidAccount")
	public ModelAndView closePostpaidAccount(@RequestParam int customerID,long mobileNo) throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		billingServices.closeCustomerPostPaidAccount(customerID,mobileNo);
		return new ModelAndView("closeAccountPage","success","Your account has been closed successfully");
	}
	@RequestMapping("/deleteCustomer")
	public ModelAndView deleteCustomer(@RequestParam int customerId) throws CustomerDetailsNotFoundException {
		billingServices.removeCustomerDetails(customerId);
		return new ModelAndView("closeAccountPage","success","Customer is removed from system");
	}
}

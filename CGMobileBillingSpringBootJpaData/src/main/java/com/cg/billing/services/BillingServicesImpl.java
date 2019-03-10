package com.cg.billing.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.daoservices.BillDao;
import com.cg.billing.daoservices.CustomerDao;
import com.cg.billing.daoservices.PlanDao;
import com.cg.billing.daoservices.PostpaidAccountDao;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
@Component("billingServices")
public class BillingServicesImpl implements BillingServices {
	@Autowired
	CustomerDao customerDao;
	@Autowired
   PlanDao planDao;
	@Autowired
   BillDao billDao;
	@Autowired
    PostpaidAccountDao postpaidAccountDao;
	
	private Customer customer;
	@Override
	public Plan getPlanAllDetails(int planId)throws PlanDetailsNotFoundException {
		Plan plan= planDao.findById(planId).orElseThrow(()->new PlanDetailsNotFoundException("Plan Not Present with Id="+planId));
			return plan;
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer) {
		customer=customerDao.save(customer);
		return customer;
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException {
			PostpaidAccount postpaidAccount=new PostpaidAccount(planDao.findById(planID).orElseThrow(()->new PlanDetailsNotFoundException("Plan Not Present with Id="+planID)), customerDao.findById(customerID).orElseThrow(()-> new CustomerDetailsNotFoundException("Customer not Present with Id="+customerID)));
			postpaidAccountDao.save(postpaidAccount);
		return postpaidAccount.getMobileNo();
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, PlanDetailsNotFoundException {
			Plan plan=getCustomerPostPaidAccountPlanDetails(customerID, mobileNo);
			PostpaidAccount postpaidAccount= getPostPaidAccountDetails(customerID, mobileNo);
			 float totalBillAmount=0, localSMSAmount=0, stdSMSAmount=0, localCallAmount=0,stdCallAmount=0, internetDataUsageAmount=0,  stateGST=0 , centralGST=0;
			if(noOfLocalCalls>plan.getFreeLocalCalls()) {
				localCallAmount=(noOfLocalCalls-plan.getFreeLocalCalls())*plan.getLocalCallRate();
			}
			if(noOfStdCalls>plan.getFreeStdCalls()) {
				stdCallAmount=(noOfStdCalls-plan.getFreeStdCalls())*plan.getStdCallRate();
			}
			if(noOfLocalSMS>plan.getFreeLocalSMS()) {
				localSMSAmount=(noOfLocalSMS-plan.getFreeLocalSMS())*plan.getLocalSMSRate();
			}
			if(noOfStdSMS>plan.getFreeStdSMS()) {
				stdSMSAmount=(noOfStdSMS-plan.getFreeStdSMS())*plan.getStdSMSRate();
			}
			if(internetDataUsageUnits>plan.getFreeInternetDataUsageUnits()) {
				internetDataUsageAmount= (internetDataUsageUnits-plan.getFreeInternetDataUsageUnits())*plan.getInternetDataUsageRate();
			}
			float sumOfBill=localCallAmount+stdCallAmount+localSMSAmount+stdSMSAmount+internetDataUsageAmount;
			 stateGST=(float) 0.3*sumOfBill;
			 centralGST=(float) 0.1*sumOfBill;
			 totalBillAmount=sumOfBill+stateGST+centralGST+plan.getMonthlyRental();
			Bill bill=new Bill(noOfLocalSMS, noOfStdSMS, noOfLocalCalls, noOfStdCalls, internetDataUsageUnits, billMonth,totalBillAmount,localSMSAmount,stdSMSAmount,localCallAmount,stdCallAmount,internetDataUsageAmount, stateGST, centralGST,postpaidAccount);
			billDao.save(bill);
			return bill.getTotalBillAmount();
	}

	@Override
	public Customer getCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		 customer=customerDao.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not present with ID="+customerID));
		return customer;
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		return customerDao.findAll();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		 customer =customerDao.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not present with ID="+customerID));
		PostpaidAccount postpaidAccount=postpaidAccountDao.findById(mobileNo).orElseThrow(()-> new PostpaidAccountNotFoundException("Account not present with mobileNo="+mobileNo));
		return postpaidAccount;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException {
		 customer =customerDao.findById(customerID).orElseThrow(()->new CustomerDetailsNotFoundException("Customer not present with ID="+customerID));
		 return new  ArrayList<PostpaidAccount>(customer.getPostpaidAccounts().values());//postpaidAccountDao.findAll(customerID);     //check this point
	}
	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException {
	      Bill bill=null;
		 ArrayList<Integer>billSetKey=new ArrayList<Integer>(getCustomerDetails(customerID).getPostpaidAccounts().get(mobileNo).getBills().keySet());
		for(int i=0;i<billSetKey.size();i++) {
			if(getPostPaidAccountDetails(customerID, mobileNo).getBills().get(billSetKey.get(i)).getBillMonth().equalsIgnoreCase(billMonth))
				bill=billDao.findById(billSetKey.get(i)).orElseThrow(()-> new InvalidBillMonthException("Bill Month is not Correct"+billMonth));		 
		}
		return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		return new ArrayList<Bill>(getPostPaidAccountDetails(customerID, mobileNo).getBills().values());
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		PostpaidAccount postpaidAccount=new PostpaidAccount(mobileNo, getPlanAllDetails(planID), getCustomerDetails(customerID));
		postpaidAccountDao.save(postpaidAccount);	
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)      
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException {
		postpaidAccountDao.delete(getPostPaidAccountDetails(customerID, mobileNo));
		return true;
	}

	@Override
	public boolean removeCustomerDetails(int customerID) throws CustomerDetailsNotFoundException {
		customerDao.delete(getCustomerDetails(customerID)); 
		return true;
	}
	
	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException {
		getCustomerDetails(customerID);
		PostpaidAccount postpaidAccount=postpaidAccountDao.findById(mobileNo).orElseThrow(()-> new PostpaidAccountNotFoundException("Account not present with mobileNo="+mobileNo));
		return postpaidAccount.getPlan();
	}

	@Override
	public List<Plan> getAllPlanDetails() {
		return planDao.findAll();
	}
}

interface PaymentProcessor{
      public void processPayment(double amount,String currency);
      public String getTransactionStatus();
}

class UpiPayment implements PaymentProcessor{
      private boolean paymentStatus;
      private String transactionId;

      @Override
      public void processPayment(double amount,String currency){
      System.out.println("UpiPayment Processing Payment of " + amount + " in " + currency);
      transactionId ="TXN_" + System.currentTimeMillis();
      this.paymentStatus = true;
      System.out.println("UpiPayment Successfull");
      }
      
      @Override
      public String getTransactionStatus(){
        return this.transactionId;
      }
}

class CheckoutService {
    private PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(double amount, String currency) {
        System.out.println("CheckoutService: Attempting to process order for $" + amount + " " + currency);
        paymentProcessor.processPayment(amount, currency);
            System.out.println("CheckoutService: Order successful! Transaction ID: " 
                               + paymentProcessor.getTransactionStatus());
        
    }
}

class LegacyGateway {
    private long transactionReference;
    private boolean isPaymentSuccessful;

    public void executeTransaction(double totalAmount, String currency) {
        System.out.println("LegacyGateway: Executing transaction for " 
                           + currency + " " + totalAmount);
        transactionReference = System.nanoTime();
        isPaymentSuccessful = true;
        System.out.println("LegacyGateway: Transaction executed successfully. Txn ID: " 
                           + transactionReference);
    }

    public boolean checkStatus(long transactionReference) {
        System.out.println("LegacyGateway: Checking status for ref: " + transactionReference);
        return isPaymentSuccessful;
    }

    public long getReferenceNumber() {
        return this.transactionReference;
    }
}
class LegacyGatewayAdapter implements PaymentProcessor {
    private final LegacyGateway legacyGateway;
    private long currentRef;

    public LegacyGatewayAdapter(LegacyGateway legacyGateway){
        this.legacyGateway = legacyGateway;
    }

     @Override
  public void processPayment(double amount, String currency) {
    System.out.println("Adapter: Translating processPayment() for " + amount + " " + currency);
     legacyGateway.executeTransaction(amount, currency);
             currentRef = legacyGateway.getReferenceNumber(); // Store for later use
 }
      
    @Override
    public String getTransactionStatus (){
        return String.valueOf(this.currentRef);
    }
}


public class Main {
public static void main(String[] args){
     
    PaymentProcessor processor = new UpiPayment();
    CheckoutService modernCheckout = new CheckoutService(processor);

    System.out.println("----Modern Checkout Service----");
    modernCheckout.checkout(199.99,"USD");


    System.out.println("---Using Legacy Gateway Payment---");
    LegacyGateway legacyGateway = new LegacyGateway();
    processor= new LegacyGatewayAdapter(legacyGateway);
    CheckoutService legacyCheckout = new CheckoutService(processor);
    legacyCheckout.checkout(75.55,"USD");
}
}

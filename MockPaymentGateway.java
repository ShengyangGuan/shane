public class MockPaymentGateway implements PaymentGateway {
    @Override
    public boolean processPayment(double amount) {
        // Simulate payment processing
        System.out.println("Processing payment of $" + amount);
        return true; // Always return true for mock
    }
}
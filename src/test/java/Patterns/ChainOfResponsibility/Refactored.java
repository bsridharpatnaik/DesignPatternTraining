// Step 1: Create abstract handler class
abstract class TestValidator {
    protected TestValidator nextValidator;

    public TestValidator setNext(TestValidator validator) {
        this.nextValidator = validator;
        return validator;
    }

    public abstract boolean validate(TestContext context);
}

// Step 2: Create concrete validators
class BrowserValidator extends TestValidator {
    @Override
    public boolean validate(TestContext context) {
        if (!checkBrowser()) {
            return false;
        }
        return nextValidator == null || nextValidator.validate(context);
    }
}

class EnvironmentValidator extends TestValidator {
    @Override
    public boolean validate(TestContext context) {
        if (!checkEnvironment()) {
            return false;
        }
        return nextValidator == null || nextValidator.validate(context);
    }
}

// Step 3: Create test using chain
public class TestExecution {
    private TestValidator validationChain;

    @BeforeClass
    public void setup() {
        // Build the chain
        validationChain = new BrowserValidator();
        validationChain
            .setNext(new EnvironmentValidator())
            .setNext(new DataValidator())
            .setNext(new PermissionValidator());
    }

    @Test
    public void runTest() {
        TestContext context = new TestContext();
        if (validationChain.validate(context)) {
            runActualTest();
        }
    }
}

/*
Key Changes:
1. Each validation in separate class
2. Validators can be chained flexibly
3. Easy to add/remove validators
4. Can reuse validators across tests
5. Dynamic validation order
6. Single Responsibility Principle followed
7. Clean test methods
*/

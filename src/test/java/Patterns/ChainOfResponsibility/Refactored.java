// Refactored Code using Chain of Responsibility

/**
 * Abstract handler defining the contract for all validators
 */
public abstract class TestValidator {
    protected TestValidator nextValidator;

    public TestValidator setNext(TestValidator validator) {
        this.nextValidator = validator;
        return validator;
    }

    public abstract boolean validate(TestContext context);
}

/**
 * Context object holding test information
 */
public class TestContext {
    private String browser;
    private String environment;
    private Map<String, Object> testData;
    private Set<String> permissions;

    // Getters and setters
}

/**
 * Concrete validators for different checks
 */
public class BrowserValidator extends TestValidator {
    @Override
    public boolean validate(TestContext context) {
        // Check if browser is supported
        boolean isValid = isBrowserSupported(context.getBrowser());

        if (!isValid) {
            return false;
        }

        return nextValidator == null || nextValidator.validate(context);
    }
}

public class EnvironmentValidator extends TestValidator {
    @Override
    public boolean validate(TestContext context) {
        // Check if environment is ready
        boolean isValid = isEnvironmentReady(context.getEnvironment());

        if (!isValid) {
            return false;
        }

        return nextValidator == null || nextValidator.validate(context);
    }
}

public class TestDataValidator extends TestValidator {
    @Override
    public boolean validate(TestContext context) {
        // Validate test data
        boolean isValid = validateTestData(context.getTestData());

        if (!isValid) {
            return false;
        }

        return nextValidator == null || nextValidator.validate(context);
    }
}

public class PermissionValidator extends TestValidator {
    @Override
    public boolean validate(TestContext context) {
        // Check permissions
        boolean isValid = checkPermissions(context.getPermissions());

        if (!isValid) {
            return false;
        }

        return nextValidator == null || nextValidator.validate(context);
    }
}

/**
 * Test class showing usage of the chain
 */
public class TestExecution {
    private TestValidator validationChain;

    @BeforeClass
    public void setup() {
        // Build the validation chain
        validationChain = new BrowserValidator();
        validationChain
            .setNext(new EnvironmentValidator())
            .setNext(new TestDataValidator())
            .setNext(new PermissionValidator());
    }

    @Test
    public void testUserLogin() {
        // Create test context
        TestContext context = new TestContext();
        context.setBrowser("chrome");
        context.setEnvironment("staging");
        context.setTestData(Map.of("username", "testUser"));
        context.setPermissions(Set.of("LOGIN"));

        // Run validation chain
        if (!validationChain.validate(context)) {
            throw new TestException("Validation failed");
        }

        // Actual test code
        loginUser();
    }

    @Test
    public void testUserProfile() {
        TestContext context = new TestContext();
        // ... set context properties

        // Reuse same validation chain
        if (!validationChain.validate(context)) {
            throw new TestException("Validation failed");
        }

        // Test profile functionality
    }
}

/**
 * Example of dynamic chain building
 */
public class ValidationChainBuilder {
    public static TestValidator createChain(List<String> requiredValidations) {
        TestValidator firstValidator = null;
        TestValidator currentValidator = null;

        for (String validation : requiredValidations) {
            TestValidator validator = switch (validation) {
                case "BROWSER" -> new BrowserValidator();
                case "ENVIRONMENT" -> new EnvironmentValidator();
                case "TEST_DATA" -> new TestDataValidator();
                case "PERMISSIONS" -> new PermissionValidator();
                default -> throw new IllegalArgumentException("Unknown validator: " + validation);
            };

            if (firstValidator == null) {
                firstValidator = validator;
                currentValidator = validator;
            } else {
                currentValidator.setNext(validator);
                currentValidator = validator;
            }
        }

        return firstValidator;
    }
}

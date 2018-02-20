import java.io.Serializable;
import java.util.HashMap;

public class BankApp implements Serializable
{
    private static final long serialVersionUID = 1L;
    static User super_user =new Customer("admin","Password1");
    int x=0;
    HashMap<String, User> user_database=new HashMap<>();
    HashMap<Integer, Account> account_database=new HashMap<>();
    HashMap<Integer,String> applications=new HashMap<>();

    public void run()
    {
        super_user.user_database=user_database;
        super_user.account_database=account_database;
        super_user.applications=applications;
        Input reader=new Input();
        //Use file persistence
        int choice=-1;
        boolean exit=false;

        while(!exit)
        {
            System.out.println("Welcome to Fells Wargo! Please select a numerical option, or press 0 to exit");
            System.out.println("1. Login");
            System.out.println("2. New User");
            choice=reader.integer(2);

            if(choice==1)
                login();
            else if(choice==2)
                register();
            else
                exit=true;
        }

        user_database = super_user.user_database;
        account_database= super_user.account_database;
        applications= super_user.applications;

    }

    private static void login()
    {
        Input reader=new Input();
        boolean zero=false;
        String username, password;
        while(!zero)
        {
            System.out.print("Username:");
            username = reader.string();
            //test System.out.println(username);

            if (super_user.user_database.containsKey(username))
            {
                System.out.print("Password:");
                password = reader.string();
                //test System.out.println(password);

                if (super_user.user_database.get(username).password.equals(password))
                {
                    super_user.user_database.get(username).initialize_menu();
                    zero=true;
                }
                else
                    System.out.println("Password is incorrect. Please try again, or press 0 to exit");
            }
            else if(!username.equals("0"))
            {
                System.out.println("Username is incorrect. Please try again, or press 0 to exit");
            }
            else
                zero=true;

        }

    }

    private static void register()
    {
        String username, password;
        int access_level;
        Input reader = new Input();
        boolean zero = false;

        while (!zero)
        {
            System.out.print("Username:");
            username = reader.string();
            if(username.equals("0"))
                zero=true;
            else if (super_user.user_database.containsKey(username))
                System.out.println(username+" already exists. Choose another username or press 0 to exit");
            else
            {
                System.out.print("Password:");
                password = reader.string();
                System.out.println("Access Level:");
                System.out.println("1.Admin");
                System.out.println("2.Employee");
                System.out.println("3.Customer");

                access_level = reader.integer(3);

                if (access_level == 1)
                {
                    Admin user = new Admin(username, password);
                    user.initialize_menu();
                    System.out.println("Account created");
                }
                else if (access_level == 2)
                {
                    Employee user = new Employee(username, password);
                    user.initialize_menu();
                    System.out.println("Account created");
                }
                else if (access_level == 3)
                {
                    Customer user = new Customer(username, password);
                    user.initialize_menu();
                    System.out.println("Account created");
                }

                zero=true;

            }
        }

    }
}

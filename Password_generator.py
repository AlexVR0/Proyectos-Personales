print("hello, would you like to check a passowrd or generate one")
passwd = input("select 1 to generate, select 2 to check: ")

if passwd == "1":

    import random
    import string 
    
    rnd = random.randint(0,9)

    num_list = [
    [0,1,2,3,4,5,6,7,8,9]
   ]

    def random_string_gen(string_size, allowed_chars):
        return ''.join(random.choice(allowed_chars) for x in range(string_size))

    chars = string.ascii_letters + string.punctuation
    size = 15

    print("generating...")
    #print(chars)
    print('generated password: ', random_string_gen(size, chars), end="")
    print(num_list [0][rnd], end=""+'\n')
    input()

elif passwd == "2":
    password_to_check = input("Please enter the password to check: ")
    size_of_password = len(password_to_check)

    number = 0
    number_list = [1, 2, 3, 4, 5, 6, 7, 8, 9]

    if size_of_password >= 15:
        print("The Password is Secure!")
        print("To confirm that your password is secure head over to Kaspersky password checker: https://password.kaspersky.com/")
        input()
    else:
        print('Password is not s3cure'+'\n')
        input()
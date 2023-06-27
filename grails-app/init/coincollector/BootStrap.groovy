package coincollector

import com.coincollector.auth.Role
import com.coincollector.auth.User
import com.coincollector.auth.UserRole
import com.coincollector.customer.Customer

class BootStrap {

    def init = { servletContext ->
        Role roleUser = Role.findByAuthority("ROLE_USER")
        if (roleUser == null){
            roleUser = new Role(authority: "ROLE_USER").save()
        }

        User user = User.findByUsername("user@email.com")
        if (user == null) {
            Customer customer = new Customer(
                    name: 'Usuário',
                    email: 'user@email.com',
                    personType: 'PF',
                    cpfCnpj:'27035592083',
                    cep:'89211330',
                    state:'SC',
                    city:'Joinville',
                    district:'Floresta',
                    address:'Rua Álvaro Moretti',
                    addressNumber:'55',
                    complement:'Casa',
                    phoneNumber:'47996315466').save()
            user = new User(username: "user@email.com", password: "user", customer: customer, enabled: true).save()
        }

        if (!UserRole.findByUserAndRole(user, roleUser)) {
            UserRole.withTransaction {
                new UserRole(user: user, role: roleUser).save(flush:true)
            }
        }
    }
}
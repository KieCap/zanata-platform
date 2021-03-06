package org.zanata.rest.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.webcohesion.enunciate.metadata.DocumentationExample;
import com.webcohesion.enunciate.metadata.Label;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.zanata.common.Namespaces;
import org.zanata.rest.MediaTypes;
import org.zanata.rest.MediaTypes.Format;

/**
 * System user account.
 */
@XmlType(name = "accountType")
@XmlRootElement(name = "account")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "email", "name", "username", "password" })
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Label("Account")
public class Account implements Serializable, HasMediaType {

    private static final long serialVersionUID = 3271307247663618597L;
    private String email;

    private String name;

    private String username;

    private String passwordHash;

    private String apiKey;

    private boolean enabled;

    private Set<String> roles = new HashSet<String>();

    private Set<String> languages = new HashSet<String>();

    public Account() {
    }

    public Account(String email, String name, String username,
            String passwordHash) {
        this.email = email;
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    @XmlAttribute(name = "email", required = true)
    @Email
    @NotNull
    @DocumentationExample("email@example.com")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlAttribute(name = "name", required = true)
    @NotEmpty
    @DocumentationExample("Homer Simpson")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "username", required = true)
    @NotEmpty
    @DocumentationExample("homer")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlAttribute(name = "passwordHash")
    @DocumentationExample("cf23df2207d99a74fbe169e3eba035e633b65d94")
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = password;
    }

    @XmlAttribute(name = "apiKey")
    @Size(min = 32, max = 32)
    @DocumentationExample("qiyh4XPJGsOZ2MEAyLkfWqeQ")
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @XmlAttribute(name = "enabled", required = true)
    @NotNull
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @XmlElement(name = "role", namespace = Namespaces.ZANATA_OLD)
    @JsonProperty("roles")
    @DocumentationExample(value = "admin", value2 = "project-creator")
    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    /**
     * Global language teams the account belongs to
     */
    @XmlElement(name = "languages", namespace = Namespaces.ZANATA_OLD)
    @JsonProperty("languages")
    @DocumentationExample(value = "es", value2 = "ja")
    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    /**
     * Deprecated. Use {@link #getLanguages()}}
     */
    @Deprecated
    @XmlElement(name = "tribe", namespace = Namespaces.ZANATA_OLD)
    @JsonProperty("tribes")
    @DocumentationExample(value = "es", value2 = "ja")
    public Set<String> getTribes() {
        return getLanguages();
    }

    /**
     * Deprecated. Use {@link #setLanguages(Set)}
     */
    @Deprecated
    public void setTribes(Set<String> tribes) {
        setLanguages(tribes);
    }

    @Override
    public String getMediaType(Format format) {
        return MediaTypes.APPLICATION_ZANATA_ACCOUNT + format;
    }

    @Override
    public String toString() {
        return DTOUtil.toXML(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apiKey == null) ? 0 : apiKey.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result =
                prime
                        * result
                        + ((passwordHash == null) ? 0 : passwordHash.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((languages == null) ? 0 : languages.hashCode());
        result =
                prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account other = (Account) obj;
        if (apiKey == null) {
            if (other.apiKey != null) {
                return false;
            }
        } else if (!apiKey.equals(other.apiKey)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (enabled != other.enabled) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (passwordHash == null) {
            if (other.passwordHash != null) {
                return false;
            }
        } else if (!passwordHash.equals(other.passwordHash)) {
            return false;
        }
        if (roles == null) {
            if (other.roles != null) {
                return false;
            }
        } else if (!roles.equals(other.roles)) {
            return false;
        }
        if (languages == null) {
            if (other.languages != null) {
                return false;
            }
        } else if (!languages.equals(other.languages)) {
            return false;
        }
        if (username == null) {
            if (other.username != null) {
                return false;
            }
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }

}

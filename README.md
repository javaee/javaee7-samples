# About

This is a project website template living in the _gh-pages_ branch.

# IMPORTANT!

* Check that the LICENSE.md in _gh-pages_ branch matches the License in the _master_ branch!
* Make sure to configure **_config.yml** to reflect your project name, etc.
* Ideally, all project should have at least 2 sections on this page :
  * About - explaining what the project is about
  * News - covering some recent news

# Latest News

## April 10th, 2017 - JAX-RS PATCH support (client API) ##

PATCH support has been added to JAX-RS API 2.1 in milestone 6, see [here](https://java.net/projects/jax-rs-spec/lists/users/archive/2017-04/message/40).

# Some Layout Examples

## A Code Snippet

```java
/**
 * Indicates that the annotated method responds to HTTP PATCH requests.
 *
 * @author Pavel Bucek (pavel.bucek at oracle.com)
 * @see HttpMethod
 * @since 2.1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod(HttpMethod.GET)
@Documented
public @interface PATCH {
}
```

##  A Table


| File  | Description |
| :---: | :--- |
| [javax.mail.jar](https://github.com/javaee/javamail/releases/download/JAVAMAIL-1_5_6/javax.mail.jar)  | The JavaMail reference implementation, including the SMTP, IMAP, and POP3 protocol providers  |
| [README.txt](https://bshannon.github.io/test/docs/README.txt) | Overview of the release |
| [NOTES.txt](https://bshannon.github.io/test/docs/NOTES.txt)	|Additional notes about using JavaMail  |
| [SSLNOTES.txt](https://bshannon.github.io/test/docs/SSLNOTES.txt)	|Notes on using SSL/TLS with JavaMail  |


  

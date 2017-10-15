package ysemp;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class VertexServer
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route route = router.route(HttpMethod.GET, "/p1/");
        route.handler(routingContext -> {
            String val1 = routingContext.request().getParam("v1");
            HttpServerResponse response = routingContext.response();
            String res = "You are " + val1;
            response.end(res);
        });

        Route routeDefault = router.route("/*");
        routeDefault.handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.end("Hello World!");
        });

        server.requestHandler(router::accept).listen(8080);
    }

}
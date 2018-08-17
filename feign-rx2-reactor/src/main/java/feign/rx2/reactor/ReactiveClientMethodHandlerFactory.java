package feign.rx2.reactor;

import feign.MethodMetadata;
import feign.Target;
import feign.reactor.ReactiveMethodHandlerFactory;
import feign.reactor.client.ReactiveClientFactory;

import static feign.Util.checkNotNull;
import static feign.reactor.utils.FeignUtils.returnPublisherType;

public class ReactiveClientMethodHandlerFactory implements ReactiveMethodHandlerFactory {
	private final ReactiveClientFactory reactiveClientFactory;

	public ReactiveClientMethodHandlerFactory(final ReactiveClientFactory reactiveClientFactory) {
		this.reactiveClientFactory = checkNotNull(reactiveClientFactory, "client must not be null");
	}

	@Override
	public ReactiveClientMethodHandler create(Target target, final MethodMetadata metadata) {

		return new ReactiveClientMethodHandler(target, metadata,
				returnPublisherType(metadata), reactiveClientFactory.apply(metadata));
	}
}

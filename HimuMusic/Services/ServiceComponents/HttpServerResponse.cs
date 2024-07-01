namespace HimuMusic.Services.ServiceComponents
{
    public class HttpServerResponse<T>
    {
        public int StatusCode { get; set; }
        public T? Result { get; set; } = default;
    }

    public class HttpServerResponse
    {
        public int StatusCode { get; set; }
    }
}

import { HttpInterceptorFn } from '@angular/common/http';

export const appInterceptor: HttpInterceptorFn = (req, next) => {
  if (localStorage.getItem('token')) {
    const newReq = req.clone({
      headers: req.headers.append(
        'Authorization',
        `Bearer ${localStorage.getItem('token')}`
      ),
    });
    return next(newReq);
  } else {
    return next(req);
  }
};
